import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CoinWillBeListenedMySuffix } from './coin-will-be-listened-my-suffix.model';
import { CoinWillBeListenedMySuffixService } from './coin-will-be-listened-my-suffix.service';

@Component({
    selector: 'jhi-coin-will-be-listened-my-suffix-detail',
    templateUrl: './coin-will-be-listened-my-suffix-detail.component.html'
})
export class CoinWillBeListenedMySuffixDetailComponent implements OnInit, OnDestroy {

    coinWillBeListened: CoinWillBeListenedMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private coinWillBeListenedService: CoinWillBeListenedMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCoinWillBeListeneds();
    }

    load(id) {
        this.coinWillBeListenedService.find(id).subscribe((coinWillBeListened) => {
            this.coinWillBeListened = coinWillBeListened;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCoinWillBeListeneds() {
        this.eventSubscriber = this.eventManager.subscribe(
            'coinWillBeListenedListModification',
            (response) => this.load(this.coinWillBeListened.id)
        );
    }
}
