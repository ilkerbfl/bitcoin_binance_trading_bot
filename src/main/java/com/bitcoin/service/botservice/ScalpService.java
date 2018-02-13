package com.bitcoin.service.botservice;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.AggTrade;
import com.bitcoin.domain.NewOrderLocale;
import com.bitcoin.service.NewOrderLocaleService;
import com.bitcoin.service.impl.NewOrderLocaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.binance.api.client.domain.account.NewOrder.limitBuy;
import static com.binance.api.client.domain.account.NewOrder.limitSell;

/**
 * Created by İlker ÇATAK on 1/18/18.
 */
@Service
public class ScalpService {

        public BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("jzbJZDrZaXDITVPOnnHYjBG7TwpSBlqTvluifD6ezcGBpLwnKr1WKqbk5UTV0KGf", "5lCh8AbUwStt6iJnBlQqy3rmUmCKHPYSMnLIZ9bHQrNFjba6IPRwPFroclw4HcRd");
        public BinanceApiRestClient client = factory.newRestClient();
        BigDecimal sumPart=new BigDecimal(Double.valueOf("0.000015")).setScale(8,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal followingPrice=new BigDecimal(Double.valueOf("0.000030 ")).setScale(8,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal startPoint=new BigDecimal(Double.valueOf("0.002942 ")).setScale(8,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal lastPoint=new BigDecimal(Double.valueOf("0.003300 ")).setScale(8,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal winRate= new BigDecimal(Double.valueOf("1.005")).setScale(8,BigDecimal.ROUND_HALF_EVEN);
        Integer quantity=2;
        public static int counter=0;
        public String orderSymbol="ETCBTC";
        public static String staticOrderSymbol="ETCBTC";
        public List<Order> orderResponsesList=new ArrayList<Order>();
        public static Double lastPrice;

        @Autowired
        NewOrderLocaleService newOrderLocaleService;
        /**
         * Key is the aggregate trade id, and the value contains the aggregated trade data, which is
         * automatically updated whenever a new agg data stream event arrives.
         */
        private Map<Long, AggTrade> aggTradesCache;

        public ScalpService(){

        }

        public ScalpService(String symbol) {
            initializeAggTradesCache(symbol);
            startAggTradesEventStreaming(symbol);
            startUserDataStream();
        }
        //Kullanıcı bilgilerini dinler , orderda değişiklik olması satılması alınması durumunda event gelir.
        private void startUserDataStream() {
            // Then, we open a new web socket client, and provide a callback that is called on every update
            BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
            // First, we obtain a listenKey which is required to interact with the user data stream
            String listenKey = client.startUserDataStream();

            // Listen for changes in the account
            webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
                if (response.getEventType() == UserDataUpdateEvent.UserDataUpdateEventType.ACCOUNT_UPDATE) {
                    AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
                } else {
                    OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();
                    //Order kapandıysa
                    if(orderTradeUpdateEvent.getOrderStatus()==OrderStatus.FILLED){
                        //Satın alma emriyse, alınan coine satım emri ver
                        if(orderTradeUpdateEvent.getSide()==OrderSide.BUY){
                            placeSellOrderAfterBuyEvent(orderTradeUpdateEvent.getSymbol(),orderTradeUpdateEvent.getPrice());
                        }
                        if(orderTradeUpdateEvent.getSide()==OrderSide.SELL) {
                            placeBuyOrderAfterSoldEvent(orderTradeUpdateEvent.getSymbol(), orderTradeUpdateEvent.getPrice());
                            counter++;
                            if(orderTradeUpdateEvent.getSymbol().equals(orderSymbol)) {
                                removeBiggestBuyOrder();
                            }
//                        removeBuyOrderByOrderId(orderTradeUpdateEvent.getOrderId());
                        }
                    }
                }
            });
            System.out.println("Waiting for events...");
        }

        private void removeBiggestBuyOrder() {
            double highestBuyOrder=.0;
            Order orderWillBeRemoved= new Order();
            for (int i=0;i<orderResponsesList.size();i++)
            {
                if(Double.valueOf(orderResponsesList.get(i).getPrice())>highestBuyOrder){
                    highestBuyOrder=Double.valueOf(orderResponsesList.get(i).getPrice());
                    orderWillBeRemoved=orderResponsesList.get(i);
                }
            }
            orderResponsesList.remove(orderWillBeRemoved);
        }

        //Alım olayı gerçekleşince belirlenen komisyon oranı eklenip satım emri girilir. Order listeye eklenir
        private void placeSellOrderAfterBuyEvent(String symbol, String price) {
            newOrderLocaleService.calculateOrderPropertiesThenPlaceOrder(new NewOrderLocale(symbol,price,OrderSide.SELL));
            BigDecimal buyValueAfterSell=new BigDecimal(Double.valueOf(price)).setScale(8,BigDecimal.ROUND_HALF_EVEN);
            BigDecimal tempDeci=buyValueAfterSell.add(sumPart);
            NewOrderResponse   newOrderResponse = client.newOrder(limitSell(symbol, TimeInForce.GTC, quantity.toString(), tempDeci.toString()));
            createOrderAddToListByIdAndPrice(newOrderResponse.getOrderId(),buyValueAfterSell.toString());
        }

        private void removeBuyOrderByOrderId(long orderId) {
            for (Order order:orderResponsesList) {
                if(order.getOrderId().equals(orderId)){
                    orderResponsesList.remove(order);
                }
            }
        }


        /**
         * Initializes the aggTrades cache by using the REST API.
         */
        private void initializeAggTradesCache(String symbol) {
            BinanceApiRestClient client = factory.newRestClient();
            List<AggTrade> aggTrades = client.getAggTrades(symbol.toUpperCase());
            lastPrice=Double.valueOf(aggTrades.get(499).getPrice());
            initializeOrders(symbol,startPoint);
            this.aggTradesCache = new HashMap<>();
            for (AggTrade aggTrade : aggTrades) {
                aggTradesCache.put(aggTrade.getAggregatedTradeId(), aggTrade);
            }
        }

        private void initializeOrders(String symbol,BigDecimal orderStartPoint) {
            BigDecimal orderValue=orderStartPoint.add(sumPart);
            BigDecimal priceDecimal=new BigDecimal(lastPrice).setScale(8,BigDecimal.ROUND_HALF_EVEN);
            while (orderValue.compareTo(priceDecimal)<0&&orderValue.compareTo(lastPoint)<0){
                NewOrderResponse newOrderResponse=new NewOrderResponse();
                newOrderResponse = client.newOrder(limitBuy(symbol, TimeInForce.GTC, quantity.toString(), orderValue.toString()));
                createOrderAddToListByIdAndPrice(newOrderResponse.getOrderId(),orderValue.toString());
                BigDecimal tempDeci=orderValue.add(sumPart);
                orderValue=tempDeci;
            }
        }

        /**
         * Begins streaming of agg trades events.
         */
        private void startAggTradesEventStreaming(String symbol) {
            BinanceApiWebSocketClient client = factory.newWebSocketClient();

            client.onAggTradeEvent(symbol.toLowerCase(), response -> {
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
                lastPrice= Double.valueOf(updateAggTrade.getPrice());
//            placeBuyOrderIfNecessary();
                // Store the updated agg trade in the cache
                aggTradesCache.put(aggregatedTradeId, updateAggTrade);
//            System.out.println("LAST PRICE :  "+lastPrice);
            });
        }

        private void placeBuyOrderIfNecessary() {
            Double highestOrderValue=.0;
            for (Order order:orderResponsesList) {
                if(Double.valueOf(order.getPrice())>highestOrderValue){
                    highestOrderValue=Double.valueOf(order.getPrice());
                }
            }
            BigDecimal highestPrice=new BigDecimal(highestOrderValue).setScale(8,BigDecimal.ROUND_HALF_EVEN);
            BigDecimal lastPriceDecimal=new BigDecimal(lastPrice).setScale(8,BigDecimal.ROUND_HALF_EVEN);
            BigDecimal lastPriceTemp=highestPrice.add(followingPrice);
//        System.out.println("Last Order  " +lastPriceTemp.toStrinyg() +" ++++++ "+"Last price " +lastPriceDecimal+"  koşul:"+(lastPriceTemp.compareTo(lastPriceDecimal)<0) );
            if(lastPriceTemp.compareTo(lastPriceDecimal)<0) {
                initializeOrders(orderSymbol, highestPrice);
            }
        }

        private void placeBuyOrderAfterSoldEvent(String symbol, String soldPrice) {
            System.out.println("ITEM SOLD FOR : "+soldPrice+" SUCCESS COUNT:"+counter);
            BigDecimal soldValue=new BigDecimal(Double.valueOf(soldPrice)).setScale(8,BigDecimal.ROUND_HALF_EVEN);
            BigDecimal tempDeci= soldValue.subtract(sumPart);
            NewOrderResponse newOrderResponse = client.newOrder(limitBuy(symbol, TimeInForce.GTC, quantity.toString(), tempDeci.toString()));
            createOrderAddToListByIdAndPrice(newOrderResponse.getOrderId(),soldValue.toString());
            System.out.println("BUY ORDER PLACED : " +tempDeci.toString());

        }

        private void createOrderAddToListByIdAndPrice(Long orderId,String soldValue ) {
            Order order= new Order();
            order.setPrice(soldValue);
            order.setOrderId(orderId);
            orderResponsesList.add(order);
        }
        /**
         * @return an aggTrades cache, containing the aggregated trade id as the key,
         * and the agg trade data as the value.
         */
        public Map<Long, AggTrade> getAggTradesCache() {
            return aggTradesCache;
        }
//
//        public static void main(String[] args) {
////        BigDecimal bigDecimal = Utility.convertStringToBigDecimalRoundAsStringLength("0.1234567");
////        System.out.println("asd");
////        public BinanceApiClientFactory factory1 = BinanceApiClientFactory.newInstance("jzbJZDrZaXDITVPOnnHYjBG7TwpSBlqTvluifD6ezcGBpLwnKr1WKqbk5UTV0KGf", "5lCh8AbUwStt6iJnBlQqy3rmUmCKHPYSMnLIZ9bHQrNFjba6IPRwPFroclw4HcRd");
//
//            new ScalpService(staticOrderSymbol);
////        new CrossSellStrategy(factory1.newRestClient());
//        }
}
