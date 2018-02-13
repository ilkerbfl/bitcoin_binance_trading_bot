package com.bitcoin.wrapper.utils;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBook;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBookEntry;
import com.bitcoin.wrapper.dto.BuyOrderTotalSellOrderTotal;
import com.bitcoin.wrapper.wrapper.BittrexConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import static com.bitcoin.wrapper.utils.MarketUtils.marketNameList;

/**
 * Created by İlker ÇATAK on 12/6/17.
 */
public class OrderUtils {

//    static BittrexConverter bittrexConverter= new BittrexConverter();
//
//    public static boolean getCondition(Double rate, Double limit, boolean sellTrueBuyFalse) {
//        return sellTrueBuyFalse ? rate<limit : rate>limit;
//    }
//
//    public static Double getLimitByPercentageRateAndOrderType(Double last, int i, boolean sellTrueBuyFalse) {
//        return sellTrueBuyFalse ? last*(1+(Double.valueOf(i)/100)):last*(1-(Double.valueOf(i)/100));
//    }
//
//
//    public static void scanOrdersEveryPercentage(Double last, boolean sellTrueBuyFalse, List<OrderBookEntry> orderBook, HashMap<Integer, BuyOrderTotalSellOrderTotal> percentageTotal) {
//        int listCursor=0;
//        double tempTotal=.0;
//        Double limit=  OrderUtils.getLimitByPercentageRateAndOrderType(last,1,sellTrueBuyFalse);
//        for (int i=1;i<=100;i++){
//            for (int j = listCursor ; j<orderBook.size();j++) {
//                if (OrderUtils.getCondition(orderBook.get(listCursor).getRate(), limit, sellTrueBuyFalse)) {
//                    tempTotal += getBtcValueOfOrderByQuantityAndLast(orderBook.get(listCursor).getQuantity(),last);
//                    listCursor++;
//                } else {
//                    putToMap(percentageTotal, sellTrueBuyFalse, i, tempTotal);
//                    limit = OrderUtils.getLimitByPercentageRateAndOrderType(last, i, sellTrueBuyFalse);
//                    //burda bir sonraki percentageda order yoksa diğerine eklemiş oluyor.
//                    tempTotal = getBtcValueOfOrderByQuantityAndLast(orderBook.get(listCursor).getQuantity(),last);
//                    listCursor++;
//                    break;
//                }
//            }
//        }
//    }
//
//    private static double getBtcValueOfOrderByQuantityAndLast(Double quantity, Double last) {
//        return quantity*last;
//    }
//
//    public static HashMap<Integer, BuyOrderTotalSellOrderTotal> scanOrdersEveryPercentageByOrderBook( OrderBook orderBook, String marketName) {
//        Double last = MarketUtils.getLastPriceOfGivenMarketName(marketName);
//        HashMap<Integer,BuyOrderTotalSellOrderTotal> percentageTotal = new HashMap<Integer, BuyOrderTotalSellOrderTotal>();
//        scanOrdersEveryPercentage(last,true,orderBook.getSell(),percentageTotal);
//        scanOrdersEveryPercentage(last,false,orderBook.getBuy(),percentageTotal);
//
//       return percentageTotal;
////        printBuyOrderSellOrderMap(percentageTotal,belowPercentageLimit,abovePercentageLimit,marketName,last);
//
//    }
//    public static void printBuyOrderSellOrderMap(HashMap<Integer, BuyOrderTotalSellOrderTotal> percentageTotal, String marketName, Double last,Double buyOrderVolume,Double ratio) {
//        Double buyOrderTotal=.0;
//        Double sellOrderTotal=.0;
//        for (int i=1;i<=100;i++){
//            buyOrderTotal+=percentageTotal.get(i).getBuyOrder();
//            sellOrderTotal+=percentageTotal.get(i).getSellOrder();
//            if(buyOrderTotal>buyOrderVolume&&(buyOrderTotal/sellOrderTotal)>ratio){
//                System.out.printf("%s : buy order :%.3f  sell order: %.3f %d ratio:  %f  last:  %e   targets:  kar kapat : %e  zarar kapat:   %e \n ",marketName,buyOrderTotal,sellOrderTotal,i,buyOrderTotal/sellOrderTotal, last, last*(1+( Double.valueOf(i))/100),last*(1-( Double.valueOf(i))/100));
//            }
//        }
//    }
//
//
//    private static void putToMap(HashMap<Integer, BuyOrderTotalSellOrderTotal> percentageTotal, boolean sellTrueBuyFalse, int i,Double tempTotal) {
//        if(!percentageTotal.containsKey(i)){
//            percentageTotal.put(i,new BuyOrderTotalSellOrderTotal());
//        }
//        if(sellTrueBuyFalse){
//            percentageTotal.get(i).setSellOrder(tempTotal);
//        }else{
//            percentageTotal.get(i).setBuyOrder(tempTotal);
//        }
//    }
//
//    public static BuyOrderTotalSellOrderTotal compareOrdersByMarketNameOrderCount(String marketName, int orderCount) {
//        OrderBook orderBookObject = bittrexConverter.getOrderBookObject(marketName);
//        return iterateThroughListReturnDto(orderBookObject, orderCount, marketName);
//
//    }
//
//    private static BuyOrderTotalSellOrderTotal iterateThroughListReturnDto(OrderBook orderBook, int iterateCount, String marketName) {
//        BuyOrderTotalSellOrderTotal buyOrderTotalSellOrderTotal= new BuyOrderTotalSellOrderTotal(marketName,MarketUtils.getLastPriceOfGivenMarketName(marketName));
//        iterateOrderBook(buyOrderTotalSellOrderTotal,orderBook.getBuy(),iterateCount,false);
//        iterateOrderBook(buyOrderTotalSellOrderTotal,orderBook.getSell(),iterateCount,true);
//        return buyOrderTotalSellOrderTotal;
//    }
//
//    private static void iterateOrderBook(BuyOrderTotalSellOrderTotal buyOrderTotalSellOrderTotal, List<OrderBookEntry> orderBookEntries, int iterateCount,boolean sellTrueBuyFalse) {
//        Double quantity=.0;
//        for (int i=0;i<=iterateCount;i++){
//               quantity+=getBtcValueOfOrderByQuantityAndLast(orderBookEntries.get(i).getQuantity(),buyOrderTotalSellOrderTotal.getLast()) ;
//        }
//        if(sellTrueBuyFalse){
//            buyOrderTotalSellOrderTotal.setSellOrder(quantity);
//        }else {
//            buyOrderTotalSellOrderTotal.setBuyOrder(quantity);
//        }
//    }
//
//
//    public static List<BuyOrderTotalSellOrderTotal> getPopularMarketsFirstOrders(int count) {
//        List<BuyOrderTotalSellOrderTotal> buyOrderTotalSellOrderTotals= new ArrayList<>();
//        for (String marketName:marketNameList) {
//            buyOrderTotalSellOrderTotals.add(compareOrdersByMarketNameOrderCount(marketName,count));
//        }
//        return buyOrderTotalSellOrderTotals;
//    }
}
