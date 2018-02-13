import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CoinWillBeListenedMySuffixComponent } from './coin-will-be-listened-my-suffix.component';
import { CoinWillBeListenedMySuffixDetailComponent } from './coin-will-be-listened-my-suffix-detail.component';
import { CoinWillBeListenedMySuffixPopupComponent } from './coin-will-be-listened-my-suffix-dialog.component';
import {
    CoinWillBeListenedMySuffixDeletePopupComponent
} from './coin-will-be-listened-my-suffix-delete-dialog.component';

export const coinWillBeListenedRoute: Routes = [
    {
        path: 'coin-will-be-listened-my-suffix',
        component: CoinWillBeListenedMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.coinWillBeListened.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'coin-will-be-listened-my-suffix/:id',
        component: CoinWillBeListenedMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.coinWillBeListened.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coinWillBeListenedPopupRoute: Routes = [
    {
        path: 'coin-will-be-listened-my-suffix-new',
        component: CoinWillBeListenedMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.coinWillBeListened.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'coin-will-be-listened-my-suffix/:id/edit',
        component: CoinWillBeListenedMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.coinWillBeListened.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'coin-will-be-listened-my-suffix/:id/delete',
        component: CoinWillBeListenedMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.coinWillBeListened.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
