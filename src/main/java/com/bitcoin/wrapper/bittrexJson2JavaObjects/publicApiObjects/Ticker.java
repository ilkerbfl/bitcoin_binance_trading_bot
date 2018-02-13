package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker {

    @SerializedName("Bid")
    @Expose
    private Double bid;
    @SerializedName("Ask")
    @Expose
    private Double ask;
    @SerializedName("Last")
    @Expose
    private Double last;

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

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }
}
