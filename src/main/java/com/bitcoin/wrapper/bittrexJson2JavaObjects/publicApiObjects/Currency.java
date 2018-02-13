package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("CurrencyLong")
    @Expose
    private String currencyLong;
    @SerializedName("MinConfirmation")
    @Expose
    private Integer minConfirmation;
    @SerializedName("TxFee")
    @Expose
    private Double txFee;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CoinType")
    @Expose
    private String coinType;
    @SerializedName("BaseAddress")
    @Expose
    private Object baseAddress;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyLong() {
        return currencyLong;
    }

    public void setCurrencyLong(String currencyLong) {
        this.currencyLong = currencyLong;
    }

    public Integer getMinConfirmation() {
        return minConfirmation;
    }

    public void setMinConfirmation(Integer minConfirmation) {
        this.minConfirmation = minConfirmation;
    }

    public Double getTxFee() {
        return txFee;
    }

    public void setTxFee(Double txFee) {
        this.txFee = txFee;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public Object getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(Object baseAddress) {
        this.baseAddress = baseAddress;
    }

}
