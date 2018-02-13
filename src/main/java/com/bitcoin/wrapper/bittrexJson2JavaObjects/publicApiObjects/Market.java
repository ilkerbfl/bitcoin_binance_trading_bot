package com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Market {

    @SerializedName("MarketCurrency")
    @Expose
    private String marketCurrency;
    @SerializedName("BaseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("MarketCurrencyLong")
    @Expose
    private String marketCurrencyLong;
    @SerializedName("BaseCurrencyLong")
    @Expose
    private String baseCurrencyLong;
    @SerializedName("MinTradeSize")
    @Expose
    private Double minTradeSize;
    @SerializedName("MarketName")
    @Expose
    private String marketName;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("Created")
    @Expose
    private String created;
    @SerializedName("Notice")
    @Expose
    private String notice;
    @SerializedName("IsSponsored")
    @Expose
    private Boolean isSponsored;
    @SerializedName("LogoUrl")
    @Expose
    private String logoUrl;

    public String getMarketCurrency() {
        return marketCurrency;
    }

    public void setMarketCurrency(String marketCurrency) {
        this.marketCurrency = marketCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getMarketCurrencyLong() {
        return marketCurrencyLong;
    }

    public void setMarketCurrencyLong(String marketCurrencyLong) {
        this.marketCurrencyLong = marketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return baseCurrencyLong;
    }

    public void setBaseCurrencyLong(String baseCurrencyLong) {
        this.baseCurrencyLong = baseCurrencyLong;
    }

    public Double getMinTradeSize() {
        return minTradeSize;
    }

    public void setMinTradeSize(Double minTradeSize) {
        this.minTradeSize = minTradeSize;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Boolean getIsSponsored() {
        return isSponsored;
    }

    public void setIsSponsored(Boolean isSponsored) {
        this.isSponsored = isSponsored;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
