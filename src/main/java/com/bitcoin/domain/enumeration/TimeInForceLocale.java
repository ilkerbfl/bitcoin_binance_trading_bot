package com.bitcoin.domain.enumeration;

import com.binance.api.client.domain.TimeInForce;

/**
 * The TimeInForceLocale enumeration.
 */
public enum TimeInForceLocale {
    GTC(TimeInForce.GTC), IOC(TimeInForce.IOC);

    private final TimeInForce timeInForce;

    TimeInForceLocale(TimeInForce timeInForce) {
        this.timeInForce=timeInForce;
    }
    public TimeInForce toTimeInForce(){
        return timeInForce;
    }
}
