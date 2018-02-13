package com.bitcoin.service;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.exception.BinanceApiException;
import com.binance.api.client.impl.BinanceApiWebSocketClientImpl;
import com.bitcoin.domain.CoinWillBeListened;
import com.bitcoin.domain.NewOrderLocale;
import com.bitcoin.repository.CoinWillBeListenedRepository;
import com.bitcoin.repository.NewOrderLocaleRepository;
import com.bitcoin.service.dto.CoinWillBeListenedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by İlker ÇATAK on 1/21/18.
 */
public class BinanceConnectionService {

    public BinanceApiClientFactory factory = null;
    public BinanceApiRestClient client = null;
    BinanceApiWebSocketClient  webSocketClient = null;
    public String listenKey = null;
    BinanceApiRestClient restClient = null;
    private Map<Long, AggTrade> aggTradesCache;

    CoinWillBeListenedRepository coinWillBeListenedRepository;

    NewOrderLocaleService newOrderLocaleService;

    NewOrderLocaleRepository newOrderLocaleRepository;

    public BinanceConnectionService() {
    }

    public BinanceConnectionService(NewOrderLocaleRepository newOrderLocaleRepository, NewOrderLocaleService newOrderLocaleService, CoinWillBeListenedRepository coinWillBeListenedRepository) {
        this.coinWillBeListenedRepository = coinWillBeListenedRepository;
        this.newOrderLocaleService = newOrderLocaleService;
        this.newOrderLocaleRepository = newOrderLocaleRepository;
    }



    public void initializeConnection() {

        this.factory = BinanceApiClientFactory.newInstance("", "");
        this.client = factory.newRestClient();
        this.webSocketClient = factory.newWebSocketClient();
        listenKey = client.startUserDataStream();
        startListening();
        startUserDataStream();

    }

    public void checkOrders(){
//        client.getOpenOrders();
    }



    //Kullanıcı bilgilerini dinler , orderda değişiklik olması satılması alınması durumunda event gelir.
    public void startUserDataStream() {
        // First, we obtain a listenKey which is required to interact with the user data stream
        // Listen for changes in the account
        webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
            if (response.getEventType() == UserDataUpdateEvent.UserDataUpdateEventType.ACCOUNT_UPDATE) {
                AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
                System.out.println(accountUpdateEvent.toString());
            } else {
                OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();
                System.out.println(orderTradeUpdateEvent.toString());
                //Order kapandıysa
                if (orderTradeUpdateEvent.getOrderStatus() == OrderStatus.FILLED) {
                    NewOrder order = null;
                    //Satın alma emriyse, alınan coine satım emri ver
                    NewOrderLocale newOrderLocale = new NewOrderLocale(orderTradeUpdateEvent.getSymbol(), orderTradeUpdateEvent.getPrice());
                    if (orderTradeUpdateEvent.getSide() == OrderSide.BUY) {
                        newOrderLocale.setSide(OrderSide.BUY);
                        order = newOrderLocaleService.calculateOrderPropertiesThenPlaceOrder(newOrderLocale);
                    }
                    if (orderTradeUpdateEvent.getSide() == OrderSide.SELL) {
                        newOrderLocale.setSide(OrderSide.SELL);
                        order = newOrderLocaleService.calculateOrderPropertiesThenPlaceOrder(newOrderLocale);
                    }
                    if (order != null) {
                        try {
                            NewOrderResponse newOrderResponse = client.newOrder(order);
                            newOrderLocaleRepository.save(newOrderLocale);
                        } catch (BinanceApiException e) {
                            System.out.println(e.getError().getMsg());
                        }
                    }
                }
            }
        });
        System.out.println("Waiting for events...");
    }

    public void refresh(){
        client.keepAliveUserDataStream(listenKey);
    }



    private void startListening() {
        List<CoinWillBeListened> all = coinWillBeListenedRepository.findAll();
        for (CoinWillBeListened coinWillBeListened:all) {
            initializeAggTradesCache(coinWillBeListened.getSymbol());
            startAggTradesEventStreaming(coinWillBeListened.getSymbol());
        }
    }
    private void initializeAggTradesCache(String symbol) {
        List<AggTrade> aggTrades = client.getAggTrades(symbol.toUpperCase());
        this.aggTradesCache = new HashMap<>();
        for (AggTrade aggTrade : aggTrades) {
            aggTradesCache.put(aggTrade.getAggregatedTradeId(), aggTrade);
        }
    }

    private void startAggTradesEventStreaming(String symbol) {

        webSocketClient.onAggTradeEvent(symbol.toLowerCase(), response -> {
            Long aggregatedTradeId = response.getAggregatedTradeId();
            AggTrade updateAggTrade = aggTradesCache.get(aggregatedTradeId);
            if (updateAggTrade == null) {
                // new agg trade
                updateAggTrade = new AggTrade();
            }
            updateAggTrade.setAggregatedTradeId(aggregatedTradeId);
            updateAggTrade.setPrice(response.getPrice());
            updateAggTrade.setQuantity(response.getQuantity());
            updateAggTrade.setFirstBreakdownTradeId(response.getFirstBreakdownTradeId());
            updateAggTrade.setLastBreakdownTradeId(response.getLastBreakdownTradeId());
            updateAggTrade.setBuyerMaker(response.isBuyerMaker());
            // Store the updated agg trade in the cache
            aggTradesCache.put(aggregatedTradeId, updateAggTrade);
            BinanceConnectionManager.connectionAlive();
//            System.out.println("LAST PRICE :  "+response.getPrice());
        });
    }
    public Map<Long, AggTrade> getAggTradesCache() {
        return aggTradesCache;
    }
}
