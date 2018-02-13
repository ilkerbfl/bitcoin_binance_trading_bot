import { BaseEntity } from './../../shared';

export const enum OrderSideLocale {
    'BUY',
    'SELL'
}

export const enum OrderTypeLocale {
    'LIMIT',
    'MARKET',
    'STOP_LOSS',
    'STOP_LOSS_LIMIT',
    'TAKE_PROFIT',
    'TAKE_PROFIT_LIMIT',
    'LIMIT_MAKER'
}

export const enum TimeInForceLocale {
    'GTC',
    'IOC'
}

export class NewOrderLocaleMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public symbol?: string,
        public side?: OrderSideLocale,
        public type?: OrderTypeLocale,
        public timeInForce?: TimeInForceLocale,
        public quantity?: string,
        public price?: string,
        public newClientOrderId?: string,
        public stopPrice?: string,
        public icebergQty?: string,
        public recvWindow?: number,
        public timestamp?: number,
    ) {
    }
}
