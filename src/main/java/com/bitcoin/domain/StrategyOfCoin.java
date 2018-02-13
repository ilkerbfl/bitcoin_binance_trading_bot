package com.bitcoin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * A StrategyOfCoin.
 */
@Entity
@Table(name = "strategy_of_coin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "strategyofcoin")
public class StrategyOfCoin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sum_value", precision=10, scale=2)
    private BigDecimal sumValue;

    @Column(name = "coin_name")
    private String coinName;

    @Column(name = "scale")
    private Integer scale;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public StrategyOfCoin quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSumValue() {
        return sumValue;
    }

    public StrategyOfCoin sumValue(BigDecimal sumValue) {
        this.sumValue = sumValue;
        return this;
    }

    public void setSumValue(BigDecimal sumValue) {
        this.sumValue = sumValue;
    }

    public String getCoinName() {
        return coinName;
    }

    public StrategyOfCoin coinName(String coinName) {
        this.coinName = coinName;
        return this;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Integer getScale() {
        return scale;
    }

    public StrategyOfCoin scale(Integer scale) {
        this.scale = scale;
        return this;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
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
        StrategyOfCoin strategyOfCoin = (StrategyOfCoin) o;
        if (strategyOfCoin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), strategyOfCoin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StrategyOfCoin{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", sumValue=" + getSumValue() +
            ", coinName='" + getCoinName() + "'" +
            ", scale=" + getScale() +
            "}";
    }
}
