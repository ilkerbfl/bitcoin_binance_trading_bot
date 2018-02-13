import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { GiveOrderMySuffix } from './give-order-my-suffix.model';
import { GiveOrderMySuffixService } from './give-order-my-suffix.service';

@Component({
    selector: 'jhi-give-order-my-suffix-detail',
    templateUrl: './give-order-my-suffix-detail.component.html'
})
export class GiveOrderMySuffixDetailComponent implements OnInit, OnDestroy {

    giveOrder: GiveOrderMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private giveOrderService: GiveOrderMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGiveOrders();
    }

    load(id) {
        this.giveOrderService.find(id).subscribe((giveOrder) => {
            this.giveOrder = giveOrder;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInGiveOrders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'giveOrderListModification',
            (response) => this.load(this.giveOrder.id)
        );
    }
}
