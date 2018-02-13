package com.bitcoin.wrapper.bittrexJson2JavaObjects;


/**
 * Created by İlker ÇATAK on 12/2/17.
 */
public class PriceLimiter {

    private Double belowLimit;

    private Double aboveLimit;

    public PriceLimiter(Double belowLimit, Double aboveLimit) {
        this.belowLimit=belowLimit;
        this.aboveLimit=aboveLimit;
    }

    public PriceLimiter(double belowLimit, double aboveLimit) {
    }

    public Double getBelowLimit() {
        return belowLimit;
    }

    public void setBelowLimit(Double belowLimit) {
        this.belowLimit = belowLimit;
    }

    public Double getAboveLimit() {
        return aboveLimit;
    }

    public void setAboveLimit(Double aboveLimit) {
        this.aboveLimit = aboveLimit;
    }
}
