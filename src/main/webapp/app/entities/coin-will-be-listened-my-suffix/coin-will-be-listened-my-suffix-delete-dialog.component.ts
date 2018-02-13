import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CoinWillBeListenedMySuffix } from './coin-will-be-listened-my-suffix.model';
import { CoinWillBeListenedMySuffixPopupService } from './coin-will-be-listened-my-suffix-popup.service';
import { CoinWillBeListenedMySuffixService } from './coin-will-be-listened-my-suffix.service';

@Component({
    selector: 'jhi-coin-will-be-listened-my-suffix-delete-dialog',
    templateUrl: './coin-will-be-listened-my-suffix-delete-dialog.component.html'
})
export class CoinWillBeListenedMySuffixDeleteDialogComponent {

    coinWillBeListened: CoinWillBeListenedMySuffix;

    constructor(
        private coinWillBeListenedService: CoinWillBeListenedMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coinWillBeListenedService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'coinWillBeListenedListModification',
                content: 'Deleted an coinWillBeListened'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-coin-will-be-listened-my-suffix-delete-popup',
    template: ''
})
export class CoinWillBeListenedMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coinWillBeListenedPopupService: CoinWillBeListenedMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.coinWillBeListenedPopupService
                .open(CoinWillBeListenedMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
