package com.bitcoin.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the StrategyOfCoin entity.
 */
public class StrategyOfCoinDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private BigDecimal sumValue;

    private String coinName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSumValue() {
        return sumValue;
    }

    public void setSumValue(BigDecimal sumValue) {
        this.sumValue = sumValue;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StrategyOfCoinDTO strategyOfCoinDTO = (StrategyOfCoinDTO) o;
        if(strategyOfCoinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), strategyOfCoinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StrategyOfCoinDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", sumValue=" + getSumValue() +
            ", coinName='" + getCoinName() + "'" +
            "}";
    }
}
