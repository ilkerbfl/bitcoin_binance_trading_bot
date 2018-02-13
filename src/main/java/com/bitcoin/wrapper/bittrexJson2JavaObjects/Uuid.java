package com.bitcoin.wrapper.bittrexJson2JavaObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uuid {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
