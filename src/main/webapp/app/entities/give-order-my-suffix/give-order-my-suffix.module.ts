import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BitcoinSharedModule } from '../../shared';
import {
    GiveOrderMySuffixService,
    GiveOrderMySuffixPopupService,
    GiveOrderMySuffixComponent,
    GiveOrderMySuffixDetailComponent,
    GiveOrderMySuffixDialogComponent,
    GiveOrderMySuffixPopupComponent,
    GiveOrderMySuffixDeletePopupComponent,
    GiveOrderMySuffixDeleteDialogComponent,
    giveOrderRoute,
    giveOrderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...giveOrderRoute,
    ...giveOrderPopupRoute,
];

@NgModule({
    imports: [
        BitcoinSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GiveOrderMySuffixComponent,
        GiveOrderMySuffixDetailComponent,
        GiveOrderMySuffixDialogComponent,
        GiveOrderMySuffixDeleteDialogComponent,
        GiveOrderMySuffixPopupComponent,
        GiveOrderMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        GiveOrderMySuffixComponent,
        GiveOrderMySuffixDialogComponent,
        GiveOrderMySuffixPopupComponent,
        GiveOrderMySuffixDeleteDialogComponent,
        GiveOrderMySuffixDeletePopupComponent,
    ],
    providers: [
        GiveOrderMySuffixService,
        GiveOrderMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BitcoinGiveOrderMySuffixModule {}
