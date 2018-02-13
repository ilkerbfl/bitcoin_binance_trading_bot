package bittrexJson2JavaObjects.marketApiObjects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenOrder {

    @SerializedName("Uuid")
    @Expose
    private String uuid;
    @SerializedName("OrderUuid")
    @Expose
    private String orderUuid;
    @SerializedName("Exchange")
    @Expose
    private String exchange;
    @SerializedName("OrderType")
    @Expose
    private String orderType;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("QuantityRemaining")
    @Expose
    private Double quantityRemaining;
    @SerializedName("Limit")
    @Expose
    private Double limit;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
