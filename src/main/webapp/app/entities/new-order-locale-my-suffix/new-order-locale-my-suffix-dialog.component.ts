import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { NewOrderLocaleMySuffix } from './new-order-locale-my-suffix.model';
import { NewOrderLocaleMySuffixPopupService } from './new-order-locale-my-suffix-popup.service';
import { NewOrderLocaleMySuffixService } from './new-order-locale-my-suffix.service';

@Component({
    selector: 'jhi-new-order-locale-my-suffix-dialog',
    templateUrl: './new-order-locale-my-suffix-dialog.component.html'
})
export class NewOrderLocaleMySuffixDialogComponent implements OnInit {

    newOrderLocale: NewOrderLocaleMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private newOrderLocaleService: NewOrderLocaleMySuffixService,
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
        if (this.newOrderLocale.id !== undefined) {
            this.subscribeToSaveResponse(
                this.newOrderLocaleService.update(this.newOrderLocale));
        } else {
            this.subscribeToSaveResponse(
                this.newOrderLocaleService.create(this.newOrderLocale));
        }
    }

    private subscribeToSaveResponse(result: Observable<NewOrderLocaleMySuffix>) {
        result.subscribe((res: NewOrderLocaleMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: NewOrderLocaleMySuffix) {
        this.eventManager.broadcast({ name: 'newOrderLocaleListModification', content: 'OK'});
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
    selector: 'jhi-new-order-locale-my-suffix-popup',
    template: ''
})
export class NewOrderLocaleMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private newOrderLocalePopupService: NewOrderLocaleMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.newOrderLocalePopupService
                    .open(NewOrderLocaleMySuffixDialogComponent as Component, params['id']);
            } else {
                this.newOrderLocalePopupService
                    .open(NewOrderLocaleMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
