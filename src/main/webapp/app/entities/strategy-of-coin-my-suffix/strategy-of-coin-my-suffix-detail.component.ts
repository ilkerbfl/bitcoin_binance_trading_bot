import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { StrategyOfCoinMySuffix } from './strategy-of-coin-my-suffix.model';
import { StrategyOfCoinMySuffixService } from './strategy-of-coin-my-suffix.service';

@Component({
    selector: 'jhi-strategy-of-coin-my-suffix-detail',
    templateUrl: './strategy-of-coin-my-suffix-detail.component.html'
})
export class StrategyOfCoinMySuffixDetailComponent implements OnInit, OnDestroy {

    strategyOfCoin: StrategyOfCoinMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private strategyOfCoinService: StrategyOfCoinMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStrategyOfCoins();
    }

    load(id) {
        this.strategyOfCoinService.find(id).subscribe((strategyOfCoin) => {
            this.strategyOfCoin = strategyOfCoin;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStrategyOfCoins() {
        this.eventSubscriber = this.eventManager.subscribe(
            'strategyOfCoinListModification',
            (response) => this.load(this.strategyOfCoin.id)
        );
    }
}
