package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDepositAddressContainer {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private DepositAddress result;

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

    public DepositAddress getDepositAddress() {
        return result;
    }

    public void setDepositAddress(DepositAddress result) {
        this.result = result;
    }

}
