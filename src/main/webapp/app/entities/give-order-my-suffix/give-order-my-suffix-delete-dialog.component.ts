import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GiveOrderMySuffix } from './give-order-my-suffix.model';
import { GiveOrderMySuffixPopupService } from './give-order-my-suffix-popup.service';
import { GiveOrderMySuffixService } from './give-order-my-suffix.service';

@Component({
    selector: 'jhi-give-order-my-suffix-delete-dialog',
    templateUrl: './give-order-my-suffix-delete-dialog.component.html'
})
export class GiveOrderMySuffixDeleteDialogComponent {

    giveOrder: GiveOrderMySuffix;

    constructor(
        private giveOrderService: GiveOrderMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.giveOrderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'giveOrderListModification',
                content: 'Deleted an giveOrder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-give-order-my-suffix-delete-popup',
    template: ''
})
export class GiveOrderMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giveOrderPopupService: GiveOrderMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.giveOrderPopupService
                .open(GiveOrderMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
