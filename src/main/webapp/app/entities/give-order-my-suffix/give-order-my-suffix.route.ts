import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { GiveOrderMySuffixComponent } from './give-order-my-suffix.component';
import { GiveOrderMySuffixDetailComponent } from './give-order-my-suffix-detail.component';
import { GiveOrderMySuffixPopupComponent } from './give-order-my-suffix-dialog.component';
import { GiveOrderMySuffixDeletePopupComponent } from './give-order-my-suffix-delete-dialog.component';

export const giveOrderRoute: Routes = [
    {
        path: 'give-order-my-suffix',
        component: GiveOrderMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.giveOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'give-order-my-suffix/:id',
        component: GiveOrderMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.giveOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const giveOrderPopupRoute: Routes = [
    {
        path: 'give-order-my-suffix-new',
        component: GiveOrderMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.giveOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'give-order-my-suffix/:id/edit',
        component: GiveOrderMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.giveOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'give-order-my-suffix/:id/delete',
        component: GiveOrderMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.giveOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
