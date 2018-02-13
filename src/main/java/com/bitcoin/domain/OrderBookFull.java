package com.bitcoin.domain;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBookEntry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

/**
 * Created by İlker ÇATAK on 12/10/17.
 */
@Entity
@Table(name = "order_book_full")
public class OrderBookFull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "buy" , fetch = FetchType.EAGER)
    private List<OrderBookEntity> buy ;
    @OneToMany(mappedBy = "sell",fetch = FetchType.EAGER)
    private List<OrderBookEntity> sell ;

    @ManyToOne
    @JoinColumn(name="market_summary", nullable=false)
    private MarketSummaryEntity marketSummary;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MarketSummaryEntity getMarketSummaryEntity() {
        return marketSummary;
    }

    public void setMarketSummaryEntity(MarketSummaryEntity marketSummaryEntity) {
        this.marketSummary= marketSummaryEntity;
    }

    public List<OrderBookEntity> getBuy() {
        return buy;
    }

    public void setBuy(List<OrderBookEntity> buy) {
        this.buy = buy;
    }

    public List<OrderBookEntity> getSell() {
        return sell;
    }

    public void setSell(List<OrderBookEntity> sell) {
        this.sell = sell;
    }
}
