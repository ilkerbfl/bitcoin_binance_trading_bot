import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BitcoinSharedModule } from '../../shared';
import {
    StrategyOfCoinMySuffixService,
    StrategyOfCoinMySuffixPopupService,
    StrategyOfCoinMySuffixComponent,
    StrategyOfCoinMySuffixDetailComponent,
    StrategyOfCoinMySuffixDialogComponent,
    StrategyOfCoinMySuffixPopupComponent,
    StrategyOfCoinMySuffixDeletePopupComponent,
    StrategyOfCoinMySuffixDeleteDialogComponent,
    strategyOfCoinRoute,
    strategyOfCoinPopupRoute,
} from './';

const ENTITY_STATES = [
    ...strategyOfCoinRoute,
    ...strategyOfCoinPopupRoute,
];

@NgModule({
    imports: [
        BitcoinSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StrategyOfCoinMySuffixComponent,
        StrategyOfCoinMySuffixDetailComponent,
        StrategyOfCoinMySuffixDialogComponent,
        StrategyOfCoinMySuffixDeleteDialogComponent,
        StrategyOfCoinMySuffixPopupComponent,
        StrategyOfCoinMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        StrategyOfCoinMySuffixComponent,
        StrategyOfCoinMySuffixDialogComponent,
        StrategyOfCoinMySuffixPopupComponent,
        StrategyOfCoinMySuffixDeleteDialogComponent,
        StrategyOfCoinMySuffixDeletePopupComponent,
    ],
    providers: [
        StrategyOfCoinMySuffixService,
        StrategyOfCoinMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BitcoinStrategyOfCoinMySuffixModule {}
