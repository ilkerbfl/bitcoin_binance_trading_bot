import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { GiveOrderMySuffix } from './give-order-my-suffix.model';
import { GiveOrderMySuffixPopupService } from './give-order-my-suffix-popup.service';
import { GiveOrderMySuffixService } from './give-order-my-suffix.service';

@Component({
    selector: 'jhi-give-order-my-suffix-dialog',
    templateUrl: './give-order-my-suffix-dialog.component.html'
})
export class GiveOrderMySuffixDialogComponent implements OnInit {

    giveOrder: GiveOrderMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private giveOrderService: GiveOrderMySuffixService,
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
        if (this.giveOrder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.giveOrderService.update(this.giveOrder));
        } else {
            this.subscribeToSaveResponse(
                this.giveOrderService.create(this.giveOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<GiveOrderMySuffix>) {
        result.subscribe((res: GiveOrderMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: GiveOrderMySuffix) {
        this.eventManager.broadcast({ name: 'giveOrderListModification', content: 'OK'});
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
    selector: 'jhi-give-order-my-suffix-popup',
    template: ''
})
export class GiveOrderMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giveOrderPopupService: GiveOrderMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.giveOrderPopupService
                    .open(GiveOrderMySuffixDialogComponent as Component, params['id']);
            } else {
                this.giveOrderPopupService
                    .open(GiveOrderMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
