package com.bitcoin.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the GiveOrder entity.
 */
public class GiveOrderDTO implements Serializable {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal price;

    private Double percentOfBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getPercentOfBalance() {
        return percentOfBalance;
    }

    public void setPercentOfBalance(Double percentOfBalance) {
        this.percentOfBalance = percentOfBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GiveOrderDTO giveOrderDTO = (GiveOrderDTO) o;
        if(giveOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), giveOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GiveOrderDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", percentOfBalance=" + getPercentOfBalance() +
            "}";
    }
}
