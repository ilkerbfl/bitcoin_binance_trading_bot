package com.bitcoin.domain;


import javax.persistence.*;
import java.util.List;

/**
 * Created by İlker ÇATAK on 12/9/17.
 */
@Entity
@Table(name = "market_summary")
public class MarketSummaryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        private String marketName;

        private Double high;

        private Double low;

        private Double volume;

        private Double last;

        private Double baseVolume;

        private String timeStamp;

        private Double bid;

        private Double ask;

        private Integer openBuyOrders;

        private Integer openSellOrders;

        private Double prevDay;

        private String created;

        @OneToMany(mappedBy = "marketSummary",fetch = FetchType.EAGER)
        private List<OrderBookFull> orderBookFull;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<OrderBookFull> getOrderBookFull() {
        return orderBookFull;
    }

    public void setOrderBookFull(List<OrderBookFull> orderBookFull) {
        this.orderBookFull = orderBookFull;
    }

    public String getMarketName() {
            return marketName;
        }

        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }

        public Double getHigh() {
            return high;
        }

        public void setHigh(Double high) {
            this.high = high;
        }

        public Double getLow() {
            return low;
        }

        public void setLow(Double low) {
            this.low = low;
        }

        public Double getVolume() {
            return volume;
        }

        public void setVolume(Double volume) {
            this.volume = volume;
        }

        public Double getLast() {
            return last;
        }

        public void setLast(Double last) {
            this.last = last;
        }

        public Double getBaseVolume() {
            return baseVolume;
        }

        public void setBaseVolume(Double baseVolume) {
            this.baseVolume = baseVolume;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public Double getBid() {
            return bid;
        }

        public void setBid(Double bid) {
            this.bid = bid;
        }

        public Double getAsk() {
            return ask;
        }

        public void setAsk(Double ask) {
            this.ask = ask;
        }

        public Integer getOpenBuyOrders() {
            return openBuyOrders;
        }

        public void setOpenBuyOrders(Integer openBuyOrders) {
            this.openBuyOrders = openBuyOrders;
        }

        public Integer getOpenSellOrders() {
            return openSellOrders;
        }

        public void setOpenSellOrders(Integer openSellOrders) {
            this.openSellOrders = openSellOrders;
        }

        public Double getPrevDay() {
            return prevDay;
        }

        public void setPrevDay(Double prevDay) {
            this.prevDay = prevDay;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

    }


