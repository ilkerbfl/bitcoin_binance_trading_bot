package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects; /**
 * Created by Ned Udomkesmalee on 7/3/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMarketHistoryContainer {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Transaction> transactions = null;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setResult(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
