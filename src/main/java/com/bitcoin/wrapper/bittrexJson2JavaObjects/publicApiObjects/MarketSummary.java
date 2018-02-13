package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects; /**
 * Created by Ned Udomkesmalee on 7/13/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketSummary {

    @SerializedName("MarketName")
    @Expose
    private String marketName;
    @SerializedName("High")
    @Expose
    private Double high;
    @SerializedName("Low")
    @Expose
    private Double low;
    @SerializedName("Volume")
    @Expose
    private Double volume;
    @SerializedName("Last")
    @Expose
    private Double last;
    @SerializedName("BaseVolume")
    @Expose
    private Double baseVolume;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Bid")
    @Expose
    private Double bid;
    @SerializedName("Ask")
    @Expose
    private Double ask;
    @SerializedName("OpenBuyOrders")
    @Expose
    private Integer openBuyOrders;
    @SerializedName("OpenSellOrders")
    @Expose
    private Integer openSellOrders;
    @SerializedName("PrevDay")
    @Expose
    private Double prevDay;
    @SerializedName("Created")
    @Expose
    private String created;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Integer getOpenBuyOrders() {
        return openBuyOrders;
    }

    public void setOpenBuyOrders(Integer openBuyOrders) {
        this.openBuyOrders = openBuyOrders;
    }

    public Integer getOpenSellOrders() {
        return openSellOrders;
    }

    public void setOpenSellOrders(Integer openSellOrders) {
        this.openSellOrders = openSellOrders;
    }

    public Double getPrevDay() {
        return prevDay;
    }

    public void setPrevDay(Double prevDay) {
        this.prevDay = prevDay;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
