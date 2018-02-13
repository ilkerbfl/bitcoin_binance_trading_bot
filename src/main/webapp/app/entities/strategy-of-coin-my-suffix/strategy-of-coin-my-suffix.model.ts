import { BaseEntity } from './../../shared';

export class StrategyOfCoinMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public quantity?: number,
        public sumValue?: number,
        public coinName?: string,
        public scale?: number,
    ) {
    }
}
