package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects; /**
 * Created by Ned Udomkesmalee on 7/18/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrderBookSellOnlyContainer {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<OrderBookEntry> result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderBookEntry> getSell() {
        return result;
    }

    public void setSell(List<OrderBookEntry> result) {
        this.result = result;
    }

}
