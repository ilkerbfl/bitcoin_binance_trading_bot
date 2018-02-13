package com.bitcoin.wrapper.strategy;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBook;
import com.bitcoin.wrapper.wrapper.BittrexConverter;
import com.google.gson.Gson;
import com.bitcoin.wrapper.dto.BuyOrderTotalSellOrderTotal;
import com.bitcoin.wrapper.wrapper.Bittrex;
import com.bitcoin.wrapper.utils.MarketUtils;
import com.bitcoin.wrapper.utils.OrderUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by İlker ÇATAK on 12/6/17.
 */
public class Strategy {

//    Bittrex bittrex= new Bittrex();
//    private Gson gson= new Gson();
//    BittrexConverter converter=new BittrexConverter();
//
//
//    public HashMap<Integer, BuyOrderTotalSellOrderTotal> scanGivenMarketEveryPercentageByMarketName(String marketName) {
//        OrderBook orderBook= converter.getOrderBookObject(marketName);
//        System.out.println("RECEIVED :"+marketName);
//        Double last = MarketUtils.getLastPriceOfGivenMarketName(marketName);
//        HashMap<Integer, BuyOrderTotalSellOrderTotal> percentageOrderMap = OrderUtils.scanOrdersEveryPercentageByOrderBook(  orderBook, marketName);
//        return percentageOrderMap;
//    }
//
//    public void scanHighVolumeAndHighRateOnly(String marketName){
//        HashMap<Integer, BuyOrderTotalSellOrderTotal> percentageOrderMap = scanGivenMarketEveryPercentageByMarketName(marketName);
//        OrderUtils.printBuyOrderSellOrderMap(percentageOrderMap,marketName, MarketUtils.getLastPriceOfGivenMarketName(marketName),10.0,3.0);
//    }
//
//    public BuyOrderTotalSellOrderTotal getOrdersQuantityByMarketNameAndCount(String marketName, int count) {
//        return OrderUtils.compareOrdersByMarketNameOrderCount(marketName,count);
//    }
//
//    public List<BuyOrderTotalSellOrderTotal> getPopularMarketsFirstOrders(int count) {
//        return OrderUtils.getPopularMarketsFirstOrders(count);
//    }
}

