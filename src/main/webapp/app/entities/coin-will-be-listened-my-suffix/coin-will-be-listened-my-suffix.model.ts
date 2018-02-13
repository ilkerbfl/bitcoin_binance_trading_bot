import { BaseEntity } from './../../shared';

export class CoinWillBeListenedMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public symbol?: string,
    ) {
    }
}
