package com.bitcoin.wrapper.bittrexJson2JavaObjects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionConfirmContainer {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Uuid result;

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

    public Uuid getUuid() {
        return result;
    }

    public void setUuid(Uuid result) {
        this.result = result;
    }

}
