package com.bitcoin.wrapper;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.PriceLimiter;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.*;
import com.google.gson.Gson;
import com.bitcoin.wrapper.wrapper.Bittrex;
import org.springframework.util.CollectionUtils;
import com.bitcoin.wrapper.utils.MarketUtils;
import com.bitcoin.wrapper.strategy.Strategy;
import com.bitcoin.wrapper.utils.UserCredentials;

import java.util.List;

public class Example {
//    public static Gson gson = new Gson();
//    public static Bittrex bittrex = new Bittrex(UserCredentials.userApiKey, UserCredentials.userSecret);
//
////
////    public static void main(String[] args) {
////
//////
//////        List<MarketSummary> marketSummaries = gson.fromJson(bittrex.getMarketSummaries(), GetMarketSummariesContainer.class).getMarketSummaries();
//////        System.out.println("asdsd");
//////        Double volumeLimit= 4098.08128143;
//////        for (int i=0;i< marketSummaries.size();i++) {
//////            if(marketSummaries.get(i).getVolume()>volumeLimit){
//////                scanGivenMarketEveryPercentageByMarketName(marketSummaries.get(i).getMarketName());
//////            }
//////        }
//////        printMarket(bittrex);
////        Strategy strategy= new Strategy();
////
////        for (String  marketName: MarketUtils.marketNameList) {
////           strategy.scanHighVolumeAndHighRateOnly(marketName);
////        }
////
//////        compareBuyAndSellOrderVolume(bittrex);
//////
//////
//////        String orderBook = bittrex.getOrderBook("BTC-SYS", 1);
//////        OrderBook orderBook1 = gson.fromJson(orderBook, GetOrderBookContainer.class).getOrderBook();
////
//////        findBiggest24HGainer(bittrex);
////    }
//
//    private static void compareBuyAndSellOrderVolume(Bittrex bittrex) {
//
//        List<MarketSummary> marketSummaries = gson.fromJson(bittrex.getMarketSummaries(), GetMarketSummariesContainer.class).getMarketSummaries();
//
//        for (MarketSummary marketSummary: marketSummaries) {
//
//            String marketName = marketSummary.getMarketName();
//            String orderBookJson = bittrex.getOrderBook(marketName, 1);
//            OrderBook orderBook = gson.fromJson(orderBookJson, GetOrderBookContainer.class).getOrderBook();
//            compareBuyAndSellVolumesByOrderBookAndGivenPercentage(marketSummary,orderBook,10);
//        }
//
//
//    }
//
//    private static void compareBuyAndSellVolumesByOrderBookAndGivenPercentage(MarketSummary marketSummary, OrderBook orderBook, int scanningLimitPercentage) {
//
//        PriceLimiter priceLimiter = getLimitsOfScanningByCurrentPriceAndPercentage(marketSummary.getLast(), scanningLimitPercentage);
//
//        Double buyOrderVolume= getBuyOrderVolumeByPriceLimiter(orderBook, priceLimiter);
//        Double sellOrderVolume= getSellOrderVolumeByPriceLimiter(orderBook, priceLimiter);
//
//        if(buyOrderVolume>(sellOrderVolume*5.0) && (marketSummary.getVolume()>300000)){
//            System.out.println(marketSummary.getMarketName() +" CURRENT PRICE :"+ marketSummary.getLast()  +"  BUY ORDER VOLUME :        "+ buyOrderVolume + "    SELL ORDER VOLUME    :"+sellOrderVolume+"   ORANI  :"   +
//            buyOrderVolume/sellOrderVolume  +" volume :"+ marketSummary.getVolume()) ;
//        }
//
//
//
//        List<OrderBookEntry> sell = orderBook.getSell();
//
//
//    }
//
//    private static Double getSellOrderVolumeByPriceLimiter(OrderBook orderBook, PriceLimiter priceLimiter) {
//
//        List<OrderBookEntry> sell = orderBook.getSell();
//
//        if(CollectionUtils.isEmpty(sell)){
//            return .0;
//        }
//
//        Double totalVolume=0.0;
//        for (OrderBookEntry order: sell ) {
//            if(order.getRate()<priceLimiter.getAboveLimit()){
//                totalVolume += order.getQuantity();
//            }
//            else {
//                break;
//            }
//        }
//
//        return totalVolume;
//    }
//
//
//    private static Double getBuyOrderVolumeByPriceLimiter(OrderBook orderBook, PriceLimiter priceLimiter) {
//
//        Double totalVolume=0.0;
//
//        List<OrderBookEntry> buy = orderBook.getBuy();
//
//        if(CollectionUtils.isEmpty(buy)){
//            return .0;
//        }
//        for (OrderBookEntry order: buy ) {
//            if(order.getRate()>priceLimiter.getBelowLimit()){
//                totalVolume += order.getQuantity();
//            }
//            else {
//                break;
//            }
//        }
//
//        return totalVolume;
//    }
//
//    private static PriceLimiter getLimitsOfScanningByCurrentPriceAndPercentage(Double currentPrice, int scanningLimitPercentage) {
//       //Örneğin yüzde 10 derinlikte aramak istiyorsak alt limit= (100-10)/100 yani %90 la çarpılmış hali
//        Double belowLimit = currentPrice*((100-(double)scanningLimitPercentage)/100.0);
//        Double aboveLimit = currentPrice*((100+(double)scanningLimitPercentage)/100.0);
//
//        return new PriceLimiter(belowLimit,aboveLimit);
//    }
//
//
//    private static void printMarket(Bittrex bittrex) {
//        Bittrex.printJson(bittrex.getMarkets());
//
//        List<Market> orderBook1 = gson.fromJson(bittrex.getMarkets(), GetMarketsContainer.class).getMarkets();
//
//    }
//
//
//    private static void findBiggest24HGainer(Bittrex bittrex) {
//        Gson gson = new Gson();
//        List<MarketSummary> marketSummaries = gson.fromJson(bittrex.getMarketSummaries(), GetMarketSummariesContainer.class).getMarketSummaries();
//        String marketName = "";
//        Double percentChange = Double.NEGATIVE_INFINITY;
//        for (int i = 0; i < marketSummaries.size(); i++) {
//            MarketSummary marketSummary = marketSummaries.get(i);
//            Double lastVal = marketSummary.getLast();
//            Double prevDayVal = marketSummary.getPrevDay();
//            Double marketPercentChange = (lastVal-prevDayVal)/prevDayVal;
//            if (marketPercentChange > percentChange) {
//                marketName = marketSummary.getMarketName();
//                percentChange = marketPercentChange;
//            }
//        }
//        System.out.println("Biggest Gainer in the last 24H is " + bittrex.getMarket(marketName).getMarketCurrencyLong()
//                + " with " + Double.toString(percentChange*100).substring(0, 6) + "% change.");
//    }
}
