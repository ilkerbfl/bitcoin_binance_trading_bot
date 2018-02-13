import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BitcoinSharedModule } from '../../shared';
import {
    CoinWillBeListenedMySuffixService,
    CoinWillBeListenedMySuffixPopupService,
    CoinWillBeListenedMySuffixComponent,
    CoinWillBeListenedMySuffixDetailComponent,
    CoinWillBeListenedMySuffixDialogComponent,
    CoinWillBeListenedMySuffixPopupComponent,
    CoinWillBeListenedMySuffixDeletePopupComponent,
    CoinWillBeListenedMySuffixDeleteDialogComponent,
    coinWillBeListenedRoute,
    coinWillBeListenedPopupRoute,
} from './';

const ENTITY_STATES = [
    ...coinWillBeListenedRoute,
    ...coinWillBeListenedPopupRoute,
];

@NgModule({
    imports: [
        BitcoinSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CoinWillBeListenedMySuffixComponent,
        CoinWillBeListenedMySuffixDetailComponent,
        CoinWillBeListenedMySuffixDialogComponent,
        CoinWillBeListenedMySuffixDeleteDialogComponent,
        CoinWillBeListenedMySuffixPopupComponent,
        CoinWillBeListenedMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        CoinWillBeListenedMySuffixComponent,
        CoinWillBeListenedMySuffixDialogComponent,
        CoinWillBeListenedMySuffixPopupComponent,
        CoinWillBeListenedMySuffixDeleteDialogComponent,
        CoinWillBeListenedMySuffixDeletePopupComponent,
    ],
    providers: [
        CoinWillBeListenedMySuffixService,
        CoinWillBeListenedMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BitcoinCoinWillBeListenedMySuffixModule {}
