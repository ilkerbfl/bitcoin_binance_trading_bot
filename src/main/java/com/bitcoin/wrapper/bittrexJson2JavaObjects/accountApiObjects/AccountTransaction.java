package com.bitcoin.wrapper.bittrexJson2JavaObjects.accountApiObjects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountTransaction {

    @SerializedName("PaymentUuid")
    @Expose
    private String paymentUuid;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Opened")
    @Expose
    private String opened;
    @SerializedName("Authorized")
    @Expose
    private Boolean authorized;
    @SerializedName("PendingPayment")
    @Expose
    private Boolean pendingPayment;
    @SerializedName("TxCost")
    @Expose
    private Double txCost;
    @SerializedName("TxId")
    @Expose
    private String txId;
    @SerializedName("Canceled")
    @Expose
    private Boolean canceled;
    @SerializedName("InvalidAddress")
    @Expose
    private Boolean invalidAddress;

    public String getPaymentUuid() {
        return paymentUuid;
    }

    public void setPaymentUuid(String paymentUuid) {
        this.paymentUuid = paymentUuid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    public Boolean getPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(Boolean pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

    public Double getTxCost() {
        return txCost;
    }

    public void setTxCost(Double txCost) {
        this.txCost = txCost;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Boolean getInvalidAddress() {
        return invalidAddress;
    }

    public void setInvalidAddress(Boolean invalidAddress) {
        this.invalidAddress = invalidAddress;
    }

}
