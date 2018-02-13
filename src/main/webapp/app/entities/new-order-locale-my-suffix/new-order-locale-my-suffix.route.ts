import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { NewOrderLocaleMySuffixComponent } from './new-order-locale-my-suffix.component';
import { NewOrderLocaleMySuffixDetailComponent } from './new-order-locale-my-suffix-detail.component';
import { NewOrderLocaleMySuffixPopupComponent } from './new-order-locale-my-suffix-dialog.component';
import { NewOrderLocaleMySuffixDeletePopupComponent } from './new-order-locale-my-suffix-delete-dialog.component';

export const newOrderLocaleRoute: Routes = [
    {
        path: 'new-order-locale-my-suffix',
        component: NewOrderLocaleMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.newOrderLocale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'new-order-locale-my-suffix/:id',
        component: NewOrderLocaleMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.newOrderLocale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const newOrderLocalePopupRoute: Routes = [
    {
        path: 'new-order-locale-my-suffix-new',
        component: NewOrderLocaleMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.newOrderLocale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'new-order-locale-my-suffix/:id/edit',
        component: NewOrderLocaleMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.newOrderLocale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'new-order-locale-my-suffix/:id/delete',
        component: NewOrderLocaleMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.newOrderLocale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
