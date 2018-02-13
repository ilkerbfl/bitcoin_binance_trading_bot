import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StrategyOfCoinMySuffix } from './strategy-of-coin-my-suffix.model';
import { StrategyOfCoinMySuffixPopupService } from './strategy-of-coin-my-suffix-popup.service';
import { StrategyOfCoinMySuffixService } from './strategy-of-coin-my-suffix.service';

@Component({
    selector: 'jhi-strategy-of-coin-my-suffix-delete-dialog',
    templateUrl: './strategy-of-coin-my-suffix-delete-dialog.component.html'
})
export class StrategyOfCoinMySuffixDeleteDialogComponent {

    strategyOfCoin: StrategyOfCoinMySuffix;

    constructor(
        private strategyOfCoinService: StrategyOfCoinMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.strategyOfCoinService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'strategyOfCoinListModification',
                content: 'Deleted an strategyOfCoin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-strategy-of-coin-my-suffix-delete-popup',
    template: ''
})
export class StrategyOfCoinMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private strategyOfCoinPopupService: StrategyOfCoinMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.strategyOfCoinPopupService
                .open(StrategyOfCoinMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
