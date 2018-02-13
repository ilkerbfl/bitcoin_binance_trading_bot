import { BaseEntity } from './../../shared';

export class GiveOrderMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public quantity?: number,
        public price?: number,
        public percentOfBalance?: number,
    ) {
    }
}
