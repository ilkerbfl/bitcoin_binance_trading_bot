package com.bitcoin.service;

import com.bitcoin.domain.MarketSummaryEntity;
import com.bitcoin.domain.OrderBookEntity;
import com.bitcoin.domain.OrderBookFull;
import com.bitcoin.repository.MarketSummaryRepository;
import com.bitcoin.repository.OrderBookFullRepository;
import com.bitcoin.repository.OrderBookRepository;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.GetOrderBookContainer;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.MarketSummary;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBook;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBookEntry;
import com.bitcoin.wrapper.dto.BuyOrderTotalSellOrderTotal;
import com.bitcoin.wrapper.strategy.Strategy;
import com.bitcoin.wrapper.wrapper.Bittrex;
import com.bitcoin.wrapper.wrapper.BittrexConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by İlker ÇATAK on 12/8/17.
 */
@Service
public class OrderService {

//    private static int round=1;
//
//
//    @Autowired
//    private MarketSummaryRepository marketSummaryRepository;
//
//    @Autowired
//    private OrderBookRepository orderBookRepository;
//
////    @Autowired
////    private ModelMapper modelMapper;
//
//    @Autowired
//    private OrderBookFullRepository orderBookFullRepository;
//
//    private final Logger log = LoggerFactory.getLogger(OrderService.class);
//    Strategy strategy= new Strategy();
//    Bittrex bittrex= new Bittrex();
//    BittrexConverter bittrexConverter= new BittrexConverter();
//
//    public OrderBook getOrdersByMarketName(String marketName) {
//        Bittrex bittrex= new Bittrex("","");
//        GetOrderBookContainer deserialize = deserialize(bittrex.getOrderBook(marketName, 1), GetOrderBookContainer.class);
//        return deserialize.getOrderBook();
//    }
//
//    public <T> T deserialize(String jsonString, Class<T> clazz) {
//        GsonBuilder builder = new GsonBuilder();
//        builder.setDateFormat("MM/dd/yy HH:mm:ss");
//        Gson gson = builder.create();
//        return gson.fromJson(jsonString, clazz);
//    }
//
//    public BuyOrderTotalSellOrderTotal getOrdersQuantityByMarketNameAndCount(String marketName, int count) {
//        return strategy.getOrdersQuantityByMarketNameAndCount(marketName,count);
//    }
//
//    public List<BuyOrderTotalSellOrderTotal> getPopularMarketsFirstOrders(int count) {
//        strategy.getPopularMarketsFirstOrders(count);
//        return strategy.getPopularMarketsFirstOrders(count);
//    }
//
//    public String getBalances(){
//        return bittrex.getBalances();
//    }
//
////    @Scheduled(fixedDelay = 600000)
//    public void  getPopularMarketsFirstOrders() {
////        List<MarketSummary> marketSummaries = bittrexConverter.compareBuySellOrderCount();
////        for (MarketSummary marketSummary : marketSummaries) {
////            MarketSummaryEntity map1 = modelMapper.map(marketSummary, MarketSummaryEntity.class);
////            marketSummaryRepository.save(map1);
////            Double last =map1.getLast();
////            OrderBook orderBookObject = bittrexConverter.getOrderBookObject(map1.getMarketName());
////            OrderBookFull map = modelMapper.map(orderBookObject, OrderBookFull.class);
////            map.setMarketSummaryEntity(map1);
////            orderBookFullRepository.save(map);
////            for (OrderBookEntity orderBookEntity:map.getBuy()) {
////                orderBookEntity.setBuy(map);
////                orderBookRepository.save(orderBookEntity);
////            }
//
////            for (OrderBookEntry orderBookEntry:orderBookObject.getBuy() ) {
////                mapToEntityThenSave(orderBookEntry,OrderBookEntity.class,round,marketName,false,last);
////
////            }
////            for (OrderBookEntry orderBookEntry:orderBookObject.getSell() ) {
////                mapToEntityThenSave(orderBookEntry,OrderBookEntity.class,round,marketName,true,last);
////            }
////        }
////        round++;
//    }
//
//    private void mapToEntityThenSave(OrderBookEntry orderBookEntry, Class<OrderBookEntity> orderBookEntityClass,int round,String marketName,boolean sellTrueBuyFalse,Double last) {
////        OrderBookEntity map = modelMapper.map(orderBookEntry, orderBookEntityClass);
////        orderBookRepository.save(map);
//    }
//
////    @Scheduled(fixedDelay = 600000,initialDelay = 600000)
//    public List<MarketSummary> compareBuySellOrderCount() {
//
//        List<MarketSummary> marketSummaries = bittrexConverter.compareBuySellOrderCount();
//        for (MarketSummary marketSummary:marketSummaries) {
////            marketSummaryRepository.save(modelMapper.map(marketSummary, MarketSummaryEntity.class));
//        }
//        return bittrexConverter.compareBuySellOrderCount();
//    }
}
