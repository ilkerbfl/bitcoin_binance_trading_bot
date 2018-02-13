package com.bitcoin.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoinWillBeListened entity.
 */
public class CoinWillBeListenedDTO implements Serializable {

    private Long id;

    private String symbol;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoinWillBeListenedDTO coinWillBeListenedDTO = (CoinWillBeListenedDTO) o;
        if(coinWillBeListenedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coinWillBeListenedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoinWillBeListenedDTO{" +
            "id=" + getId() +
            ", symbol='" + getSymbol() + "'" +
            "}";
    }
}
