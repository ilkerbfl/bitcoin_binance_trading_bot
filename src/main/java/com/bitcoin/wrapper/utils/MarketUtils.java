package com.bitcoin.wrapper.utils;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.GetMarketSummariesContainer;
import com.google.gson.Gson;
import com.bitcoin.wrapper.wrapper.Bittrex;

import java.util.Arrays;
import java.util.List;

/**
 * Created by İlker ÇATAK on 12/6/17.
 */
public class MarketUtils {
//
//
//    private static Gson gson= new Gson();
//    static Bittrex bittrex = new Bittrex();
//
//    public static List<String> marketNameList= Arrays.asList(
//            "BTC-SYS",
//            "BTC-EMC2",
//            "BTC-IOP",
//            "BTC-KMD",
//            "BTC-NXT",
//            "BTC-POWR",
//            "BTC-NEO",
//            "BTC-WAVES",
//            "BTC-VTC",
//            "BTC-RISE");
//
//
//    public static Double getLastPriceOfGivenMarketName(String marketName) {
//        String marketSummary1 = bittrex.getMarketSummary(marketName);
//        GetMarketSummariesContainer getMarketSummariesContainer = gson.fromJson(marketSummary1, GetMarketSummariesContainer.class);
//        Double last = getMarketSummariesContainer.getMarketSummaries().get(0).getLast();
//        return last;
//    }
}
