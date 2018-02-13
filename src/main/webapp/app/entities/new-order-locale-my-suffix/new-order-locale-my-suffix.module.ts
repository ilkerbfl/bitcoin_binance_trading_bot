import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BitcoinSharedModule } from '../../shared';
import {
    NewOrderLocaleMySuffixService,
    NewOrderLocaleMySuffixPopupService,
    NewOrderLocaleMySuffixComponent,
    NewOrderLocaleMySuffixDetailComponent,
    NewOrderLocaleMySuffixDialogComponent,
    NewOrderLocaleMySuffixPopupComponent,
    NewOrderLocaleMySuffixDeletePopupComponent,
    NewOrderLocaleMySuffixDeleteDialogComponent,
    newOrderLocaleRoute,
    newOrderLocalePopupRoute,
} from './';

const ENTITY_STATES = [
    ...newOrderLocaleRoute,
    ...newOrderLocalePopupRoute,
];

@NgModule({
    imports: [
        BitcoinSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NewOrderLocaleMySuffixComponent,
        NewOrderLocaleMySuffixDetailComponent,
        NewOrderLocaleMySuffixDialogComponent,
        NewOrderLocaleMySuffixDeleteDialogComponent,
        NewOrderLocaleMySuffixPopupComponent,
        NewOrderLocaleMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        NewOrderLocaleMySuffixComponent,
        NewOrderLocaleMySuffixDialogComponent,
        NewOrderLocaleMySuffixPopupComponent,
        NewOrderLocaleMySuffixDeleteDialogComponent,
        NewOrderLocaleMySuffixDeletePopupComponent,
    ],
    providers: [
        NewOrderLocaleMySuffixService,
        NewOrderLocaleMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BitcoinNewOrderLocaleMySuffixModule {}
