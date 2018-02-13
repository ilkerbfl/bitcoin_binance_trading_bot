import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StrategyOfCoinMySuffix } from './strategy-of-coin-my-suffix.model';
import { StrategyOfCoinMySuffixPopupService } from './strategy-of-coin-my-suffix-popup.service';
import { StrategyOfCoinMySuffixService } from './strategy-of-coin-my-suffix.service';

@Component({
    selector: 'jhi-strategy-of-coin-my-suffix-dialog',
    templateUrl: './strategy-of-coin-my-suffix-dialog.component.html'
})
export class StrategyOfCoinMySuffixDialogComponent implements OnInit {

    strategyOfCoin: StrategyOfCoinMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private strategyOfCoinService: StrategyOfCoinMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.strategyOfCoin.id !== undefined) {
            this.subscribeToSaveResponse(
                this.strategyOfCoinService.update(this.strategyOfCoin));
        } else {
            this.subscribeToSaveResponse(
                this.strategyOfCoinService.create(this.strategyOfCoin));
        }
    }

    private subscribeToSaveResponse(result: Observable<StrategyOfCoinMySuffix>) {
        result.subscribe((res: StrategyOfCoinMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: StrategyOfCoinMySuffix) {
        this.eventManager.broadcast({ name: 'strategyOfCoinListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-strategy-of-coin-my-suffix-popup',
    template: ''
})
export class StrategyOfCoinMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private strategyOfCoinPopupService: StrategyOfCoinMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.strategyOfCoinPopupService
                    .open(StrategyOfCoinMySuffixDialogComponent as Component, params['id']);
            } else {
                this.strategyOfCoinPopupService
                    .open(StrategyOfCoinMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
