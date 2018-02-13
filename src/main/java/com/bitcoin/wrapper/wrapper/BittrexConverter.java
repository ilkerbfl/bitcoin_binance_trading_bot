package com.bitcoin.wrapper.wrapper;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.GetMarketSummariesContainer;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.GetOrderBookContainer;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.MarketSummary;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBook;
import com.bitcoin.wrapper.utils.VolumeComparator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by İlker ÇATAK on 12/9/17.
 */
public class BittrexConverter {

//    Bittrex bittrex = new Bittrex();
//    private Gson gson=new Gson();
//
//    public OrderBook getOrderBookObject(String marketName){
//        String orderBookJson = bittrex.getOrderBook(marketName, 1);
//        if (orderBookJson==null) {
//            System.out.println(marketName+" order booku gelmedi");
//            return null;
//        }
//        OrderBook orderBook = gson.fromJson(orderBookJson, GetOrderBookContainer.class).getOrderBook();
//        return orderBook;
//    }
//
//
//    public List<MarketSummary> compareBuySellOrderCount() {
//        String marketSummariesJson = bittrex.getMarketSummaries();
//        List<MarketSummary> marketSummaries = gson.fromJson(marketSummariesJson, GetMarketSummariesContainer.class).getMarketSummaries();
//        List<MarketSummary> cleanList= new ArrayList<>();
//        for (MarketSummary marketSummary:marketSummaries) {
//            if(marketSummary.getBaseVolume()>200){
//                cleanList.add(marketSummary);
//            }
//        }
//        Collections.sort(cleanList,new VolumeComparator());
//        return cleanList;
//    }
}
