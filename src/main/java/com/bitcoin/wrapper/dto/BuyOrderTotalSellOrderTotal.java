package com.bitcoin.wrapper.dto;

/**
 * Created by İlker ÇATAK on 12/5/17.
 */
public class BuyOrderTotalSellOrderTotal{

    public static final String BITTREX_MARKET_URL ="https://bittrex.com/Market/Index?MarketName=";

    private String marketName;

    private double buyOrder;

    private double sellOrder;

    private double last;

    private String bittrexUrl;

    public BuyOrderTotalSellOrderTotal(){

    }

    public BuyOrderTotalSellOrderTotal(String marketName, Double last) {
        this.marketName=marketName;
        this.last=last;
        this.bittrexUrl=BITTREX_MARKET_URL+marketName;
    }

    public String getBittrexUrl() {
        return bittrexUrl;
    }

    public void setBittrexUrl(String bittrexUrl) {
        this.bittrexUrl = bittrexUrl;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public double getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(double buyOrder) {
        this.buyOrder = buyOrder;
    }

    public double getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(double sellOrder) {
        this.sellOrder = sellOrder;
    }
}
