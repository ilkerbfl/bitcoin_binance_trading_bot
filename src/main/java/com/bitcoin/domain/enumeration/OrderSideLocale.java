package com.bitcoin.domain.enumeration;

import com.binance.api.client.domain.OrderSide; /**
 * The OrderSideLocale enumeration.
 */
public enum OrderSideLocale {
    BUY(OrderSide.BUY), SELL(OrderSide.SELL);

    private final OrderSide side;

    OrderSideLocale(OrderSide side) {
        this.side=side;
    }

    public OrderSide toSide() {
        return side;
    }
}
