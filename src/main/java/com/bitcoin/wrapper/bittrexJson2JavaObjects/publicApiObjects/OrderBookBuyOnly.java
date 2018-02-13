package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects; /**
 * Created by Ned Udomkesmalee on 7/18/17.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderBookBuyOnly {

    @SerializedName("buy")
    @Expose
    private List<OrderBookEntry> buy = null;

    public List<OrderBookEntry> getBuy() {
        return buy;
    }

    public void setBuy(List<OrderBookEntry> buy) {
        this.buy = buy;
    }

}
