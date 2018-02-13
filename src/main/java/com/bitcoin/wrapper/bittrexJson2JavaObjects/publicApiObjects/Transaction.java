package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects; /**
 * Created by Ned Udomkesmalee on 7/3/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Total")
    @Expose
    private Double total;
    @SerializedName("FillType")
    @Expose
    private String fillType;
    @SerializedName("OrderType")
    @Expose
    private String orderType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFillType() {
        return fillType;
    }

    public void setFillType(String fillType) {
        this.fillType = fillType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
