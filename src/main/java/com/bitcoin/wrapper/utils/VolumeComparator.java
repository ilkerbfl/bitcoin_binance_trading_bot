package com.bitcoin.wrapper.utils;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.MarketSummary;

import java.util.Comparator;

/**
 * Created by İlker ÇATAK on 12/9/17.
 */
public class VolumeComparator implements Comparator<MarketSummary> {

    @Override
    public int compare(MarketSummary m1,MarketSummary m2){
        Double m1Rate=Double.valueOf(m1.getOpenBuyOrders())/Double.valueOf(m1.getOpenSellOrders());
        Double m2Rate=Double.valueOf(m2.getOpenBuyOrders())/Double.valueOf(m2.getOpenSellOrders());
        return m1Rate.compareTo(m2Rate);

    }
}
