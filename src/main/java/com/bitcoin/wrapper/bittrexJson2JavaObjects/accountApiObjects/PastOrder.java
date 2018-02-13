package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PastOrder {

    @SerializedName("OrderUuid")
    @Expose
    private String orderUuid;
    @SerializedName("Exchange")
    @Expose
    private String exchange;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("Limit")
    @Expose
    private Double limit;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("QuantityRemaining")
    @Expose
    private Double quantityRemaining;
    @SerializedName("Commission")
    @Expose
    private Double commission;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("PricePerUnit")
    @Expose
    private Double pricePerUnit;
    @SerializedName("IsConditional")
    @Expose
    private Boolean isConditional;
    @SerializedName("Condition")
    @Expose
    private String condition;
    @SerializedName("ConditionTarget")
    @Expose
    private Double conditionTarget;
    @SerializedName("ImmediateOrCancel")
    @Expose
    private Boolean immediateOrCancel;

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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
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

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
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

    public Boolean getImmediateOrCancel() {
        return immediateOrCancel;
    }

    public void setImmediateOrCancel(Boolean immediateOrCancel) {
        this.immediateOrCancel = immediateOrCancel;
    }

}
