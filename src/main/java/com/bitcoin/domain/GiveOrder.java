package com.bitcoin.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * A GiveOrder.
 */
@Entity
@Table(name = "give_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "giveorder")
public class GiveOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", precision=10, scale=2)
    private BigDecimal quantity;

    @Column(name = "price", precision=10, scale=2)
    private BigDecimal price;

    @Column(name = "percent_of_balance")
    private Double percentOfBalance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public GiveOrder quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GiveOrder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getPercentOfBalance() {
        return percentOfBalance;
    }

    public GiveOrder percentOfBalance(Double percentOfBalance) {
        this.percentOfBalance = percentOfBalance;
        return this;
    }

    public void setPercentOfBalance(Double percentOfBalance) {
        this.percentOfBalance = percentOfBalance;
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
        GiveOrder giveOrder = (GiveOrder) o;
        if (giveOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), giveOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GiveOrder{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", percentOfBalance=" + getPercentOfBalance() +
            "}";
    }
}
