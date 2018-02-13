package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("AccountId")
    @Expose
    private Object accountId;
    @SerializedName("OrderUuid")
    @Expose
    private String orderUuid;
    @SerializedName("Exchange")
    @Expose
    private String exchange;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("QuantityRemaining")
    @Expose
    private Double quantityRemaining;
    @SerializedName("Limit")
    @Expose
    private Double limit;
    @SerializedName("Reserved")
    @Expose
    private Double reserved;
    @SerializedName("ReserveRemaining")
    @Expose
    private Double reserveRemaining;
    @SerializedName("CommissionReserved")
    @Expose
    private Double commissionReserved;
    @SerializedName("CommissionReserveRemaining")
    @Expose
    private Double commissionReserveRemaining;
    @SerializedName("CommissionPaid")
    @Expose
    private Double commissionPaid;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("PricePerUnit")
    @Expose
    private Double pricePerUnit;
    @SerializedName("Opened")
    @Expose
    private String opened;
    @SerializedName("Closed")
    @Expose
    private String closed;
    @SerializedName("IsOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("Sentinel")
    @Expose
    private String sentinel;
    @SerializedName("CancelInitiated")
    @Expose
    private Boolean cancelInitiated;
    @SerializedName("ImmediateOrCancel")
    @Expose
    private Boolean immediateOrCancel;
    @SerializedName("IsConditional")
    @Expose
    private Boolean isConditional;
    @SerializedName("Condition")
    @Expose
    private String condition;
    @SerializedName("ConditionTarget")
    @Expose
    private Double conditionTarget;

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(Double quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Double getReserved() {
        return reserved;
    }

    public void setReserved(Double reserved) {
        this.reserved = reserved;
    }

    public Double getReserveRemaining() {
        return reserveRemaining;
    }

    public void setReserveRemaining(Double reserveRemaining) {
        this.reserveRemaining = reserveRemaining;
    }

    public Double getCommissionReserved() {
        return commissionReserved;
    }

    public void setCommissionReserved(Double commissionReserved) {
        this.commissionReserved = commissionReserved;
    }

    public Double getCommissionReserveRemaining() {
        return commissionReserveRemaining;
    }

    public void setCommissionReserveRemaining(Double commissionReserveRemaining) {
        this.commissionReserveRemaining = commissionReserveRemaining;
    }

    public Double getCommissionPaid() {
        return commissionPaid;
    }

    public void setCommissionPaid(Double commissionPaid) {
        this.commissionPaid = commissionPaid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getSentinel() {
        return sentinel;
    }

    public void setSentinel(String sentinel) {
        this.sentinel = sentinel;
    }

    public Boolean getCancelInitiated() {
        return cancelInitiated;
    }

    public void setCancelInitiated(Boolean cancelInitiated) {
        this.cancelInitiated = cancelInitiated;
    }

    public Boolean getImmediateOrCancel() {
        return immediateOrCancel;
    }

    public void setImmediateOrCancel(Boolean immediateOrCancel) {
        this.immediateOrCancel = immediateOrCancel;
    }

    public Boolean getIsConditional() {
        return isConditional;
    }

    public void setIsConditional(Boolean isConditional) {
        this.isConditional = isConditional;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getConditionTarget() {
        return conditionTarget;
    }

    public void setConditionTarget(Double conditionTarget) {
        this.conditionTarget = conditionTarget;
    }

}
