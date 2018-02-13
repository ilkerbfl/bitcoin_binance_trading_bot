package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import java.util.List;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.accountApiObjects.AccountTransaction;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDepositHistoryContainer {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<AccountTransaction> result = null;

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

    public List<AccountTransaction> getDeposits() {
        return result;
    }

    public void setDeposits(List<AccountTransaction> result) {
        this.result = result;
    }

}
