import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NewOrderLocaleMySuffix } from './new-order-locale-my-suffix.model';
import { NewOrderLocaleMySuffixPopupService } from './new-order-locale-my-suffix-popup.service';
import { NewOrderLocaleMySuffixService } from './new-order-locale-my-suffix.service';

@Component({
    selector: 'jhi-new-order-locale-my-suffix-delete-dialog',
    templateUrl: './new-order-locale-my-suffix-delete-dialog.component.html'
})
export class NewOrderLocaleMySuffixDeleteDialogComponent {

    newOrderLocale: NewOrderLocaleMySuffix;

    constructor(
        private newOrderLocaleService: NewOrderLocaleMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.newOrderLocaleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'newOrderLocaleListModification',
                content: 'Deleted an newOrderLocale'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-new-order-locale-my-suffix-delete-popup',
    template: ''
})
export class NewOrderLocaleMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private newOrderLocalePopupService: NewOrderLocaleMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.newOrderLocalePopupService
                .open(NewOrderLocaleMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
