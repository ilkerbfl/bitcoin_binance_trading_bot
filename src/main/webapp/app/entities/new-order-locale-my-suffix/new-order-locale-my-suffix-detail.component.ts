import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { NewOrderLocaleMySuffix } from './new-order-locale-my-suffix.model';
import { NewOrderLocaleMySuffixService } from './new-order-locale-my-suffix.service';

@Component({
    selector: 'jhi-new-order-locale-my-suffix-detail',
    templateUrl: './new-order-locale-my-suffix-detail.component.html'
})
export class NewOrderLocaleMySuffixDetailComponent implements OnInit, OnDestroy {

    newOrderLocale: NewOrderLocaleMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private newOrderLocaleService: NewOrderLocaleMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNewOrderLocales();
    }

    load(id) {
        this.newOrderLocaleService.find(id).subscribe((newOrderLocale) => {
            this.newOrderLocale = newOrderLocale;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNewOrderLocales() {
        this.eventSubscriber = this.eventManager.subscribe(
            'newOrderLocaleListModification',
            (response) => this.load(this.newOrderLocale.id)
        );
    }
}
