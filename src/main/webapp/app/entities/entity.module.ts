import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BitcoinGiveOrderMySuffixModule } from './give-order-my-suffix/give-order-my-suffix.module';
import { BitcoinStrategyOfCoinMySuffixModule } from './strategy-of-coin-my-suffix/strategy-of-coin-my-suffix.module';
import { BitcoinNewOrderLocaleMySuffixModule } from './new-order-locale-my-suffix/new-order-locale-my-suffix.module';
import { BitcoinCoinWillBeListenedMySuffixModule } from './coin-will-be-listened-my-suffix/coin-will-be-listened-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BitcoinGiveOrderMySuffixModule,
        BitcoinStrategyOfCoinMySuffixModule,
        BitcoinNewOrderLocaleMySuffixModule,
        BitcoinCoinWillBeListenedMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BitcoinEntityModule {}
