package com.bitcoin.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.Exported;

import javax.persistence.*;

/**
 * Created by İlker ÇATAK on 12/8/17.
 */
@Entity
@Table(name = "order_book_entity")
public class OrderBookEntity extends AbstractAuditingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    private Double rate;

    private int round;

    @ManyToOne
    private OrderBookFull buy;

    @ManyToOne
    private OrderBookFull sell;




    public OrderBookFull getBuy() {
        return buy;
    }

    public void setBuy(OrderBookFull buy) {
        this.buy = buy;
    }

    public OrderBookFull getSell() {
        return sell;
    }

    public void setSell(OrderBookFull sell) {
        this.sell = sell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
