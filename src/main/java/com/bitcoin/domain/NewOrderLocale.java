package com.bitcoin.domain;

import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;



/**
 * A NewOrderLocale.
 */
@Entity
@Table(name = "new_order_locale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "neworderlocale")
public class NewOrderLocale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "side")
    private OrderSide side;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private OrderType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_in_force")
    private TimeInForce timeInForce;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;

    @Column(name = "new_client_order_id")
    private String newClientOrderId;

    @Column(name = "stop_price")
    private String stopPrice;

    @Column(name = "iceberg_qty")
    private String icebergQty;

    @Column(name = "recv_window")
    private Long recvWindow;

    @Column(name = "jhi_timestamp")
    private Long timestamp;

    public NewOrderLocale(){

    }

    public NewOrderLocale(String symbol, String price, OrderSide sell) {
        this(symbol,price);
        this.side=sell;
    }

    public NewOrderLocale(String symbol, String price) {
        this.symbol=symbol;
        this.price=price;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public NewOrderLocale symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OrderSide getSide() {
        return side;
    }

    public NewOrderLocale side(OrderSide side) {
        this.side = side;
        return this;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public OrderType getType() {
        return type;
    }

    public NewOrderLocale type(OrderType type) {
        this.type = type;
        return this;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public NewOrderLocale timeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getQuantity() {
        return quantity;
    }

    public NewOrderLocale quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public NewOrderLocale price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public NewOrderLocale newClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
        return this;
    }

    public void setNewClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public NewOrderLocale stopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getIcebergQty() {
        return icebergQty;
    }

    public NewOrderLocale icebergQty(String icebergQty) {
        this.icebergQty = icebergQty;
        return this;
    }

    public void setIcebergQty(String icebergQty) {
        this.icebergQty = icebergQty;
    }

    public Long getRecvWindow() {
        return recvWindow;
    }

    public NewOrderLocale recvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
        return this;
    }

    public void setRecvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public NewOrderLocale timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewOrderLocale newOrderLocale = (NewOrderLocale) o;
        if (newOrderLocale.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newOrderLocale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewOrderLocale{" +
            "id=" + getId() +
            ", symbol='" + getSymbol() + "'" +
//            ", side='" + getSide() + "'" +
//            ", type='" + getType() + "'" +
//            ", timeInForce='" + getTimeInForce() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", newClientOrderId='" + getNewClientOrderId() + "'" +
            ", stopPrice='" + getStopPrice() + "'" +
            ", icebergQty='" + getIcebergQty() + "'" +
            ", recvWindow=" + getRecvWindow() +
            ", timestamp=" + getTimestamp() +
            "}";
    }
}
