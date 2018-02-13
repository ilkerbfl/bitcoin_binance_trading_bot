import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { StrategyOfCoinMySuffixComponent } from './strategy-of-coin-my-suffix.component';
import { StrategyOfCoinMySuffixDetailComponent } from './strategy-of-coin-my-suffix-detail.component';
import { StrategyOfCoinMySuffixPopupComponent } from './strategy-of-coin-my-suffix-dialog.component';
import { StrategyOfCoinMySuffixDeletePopupComponent } from './strategy-of-coin-my-suffix-delete-dialog.component';

export const strategyOfCoinRoute: Routes = [
    {
        path: 'strategy-of-coin-my-suffix',
        component: StrategyOfCoinMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.strategyOfCoin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'strategy-of-coin-my-suffix/:id',
        component: StrategyOfCoinMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.strategyOfCoin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const strategyOfCoinPopupRoute: Routes = [
    {
        path: 'strategy-of-coin-my-suffix-new',
        component: StrategyOfCoinMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.strategyOfCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'strategy-of-coin-my-suffix/:id/edit',
        component: StrategyOfCoinMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.strategyOfCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'strategy-of-coin-my-suffix/:id/delete',
        component: StrategyOfCoinMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bitcoinApp.strategyOfCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
