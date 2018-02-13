package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositAddress {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Balance")
    @Expose
    private Double balance;
    @SerializedName("Available")
    @Expose
    private Double available;
    @SerializedName("Pending")
    @Expose
    private Double pending;
    @SerializedName("CryptoAddress")
    @Expose
    private String cryptoAddress;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getPending() {
        return pending;
    }

    public void setPending(Double pending) {
        this.pending = pending;
    }

    public String getCryptoAddress() {
        return cryptoAddress;
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }

}
