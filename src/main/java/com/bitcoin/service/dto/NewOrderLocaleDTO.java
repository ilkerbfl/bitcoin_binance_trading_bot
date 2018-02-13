package com.bitcoin.service.dto;


import java.io.Serializable;
import java.util.Objects;
import com.bitcoin.domain.enumeration.OrderSideLocale;
import com.bitcoin.domain.enumeration.OrderTypeLocale;
import com.bitcoin.domain.enumeration.TimeInForceLocale;

/**
 * A DTO for the NewOrderLocale entity.
 */
public class NewOrderLocaleDTO implements Serializable {

    private Long id;

    private String symbol;

    private OrderSideLocale side;

    private OrderTypeLocale type;

    private TimeInForceLocale timeInForce;

    private String quantity;

    private String price;

    private String newClientOrderId;

    private String stopPrice;

    private String icebergQty;

    private Long recvWindow;

    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OrderSideLocale getSide() {
        return side;
    }

    public void setSide(OrderSideLocale side) {
        this.side = side;
    }

    public OrderTypeLocale getType() {
        return type;
    }

    public void setType(OrderTypeLocale type) {
        this.type = type;
    }

    public TimeInForceLocale getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForceLocale timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public void setNewClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getIcebergQty() {
        return icebergQty;
    }

    public void setIcebergQty(String icebergQty) {
        this.icebergQty = icebergQty;
    }

    public Long getRecvWindow() {
        return recvWindow;
    }

    public void setRecvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewOrderLocaleDTO newOrderLocaleDTO = (NewOrderLocaleDTO) o;
        if(newOrderLocaleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newOrderLocaleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewOrderLocaleDTO{" +
            "id=" + getId() +
            ", symbol='" + getSymbol() + "'" +
            ", side='" + getSide() + "'" +
            ", type='" + getType() + "'" +
            ", timeInForce='" + getTimeInForce() + "'" +
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
