import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CoinWillBeListenedMySuffix } from './coin-will-be-listened-my-suffix.model';
import { CoinWillBeListenedMySuffixPopupService } from './coin-will-be-listened-my-suffix-popup.service';
import { CoinWillBeListenedMySuffixService } from './coin-will-be-listened-my-suffix.service';

@Component({
    selector: 'jhi-coin-will-be-listened-my-suffix-dialog',
    templateUrl: './coin-will-be-listened-my-suffix-dialog.component.html'
})
export class CoinWillBeListenedMySuffixDialogComponent implements OnInit {

    coinWillBeListened: CoinWillBeListenedMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private coinWillBeListenedService: CoinWillBeListenedMySuffixService,
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
        if (this.coinWillBeListened.id !== undefined) {
            this.subscribeToSaveResponse(
                this.coinWillBeListenedService.update(this.coinWillBeListened));
        } else {
            this.subscribeToSaveResponse(
                this.coinWillBeListenedService.create(this.coinWillBeListened));
        }
    }

    private subscribeToSaveResponse(result: Observable<CoinWillBeListenedMySuffix>) {
        result.subscribe((res: CoinWillBeListenedMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CoinWillBeListenedMySuffix) {
        this.eventManager.broadcast({ name: 'coinWillBeListenedListModification', content: 'OK'});
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
    selector: 'jhi-coin-will-be-listened-my-suffix-popup',
    template: ''
})
export class CoinWillBeListenedMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coinWillBeListenedPopupService: CoinWillBeListenedMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.coinWillBeListenedPopupService
                    .open(CoinWillBeListenedMySuffixDialogComponent as Component, params['id']);
            } else {
                this.coinWillBeListenedPopupService
                    .open(CoinWillBeListenedMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
