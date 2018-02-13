import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CoinWillBeListenedMySuffix } from './coin-will-be-listened-my-suffix.model';
import { CoinWillBeListenedMySuffixService } from './coin-will-be-listened-my-suffix.service';

@Injectable()
export class CoinWillBeListenedMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private coinWillBeListenedService: CoinWillBeListenedMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.coinWillBeListenedService.find(id).subscribe((coinWillBeListened) => {
                    this.ngbModalRef = this.coinWillBeListenedModalRef(component, coinWillBeListened);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.coinWillBeListenedModalRef(component, new CoinWillBeListenedMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    coinWillBeListenedModalRef(component: Component, coinWillBeListened: CoinWillBeListenedMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.coinWillBeListened = coinWillBeListened;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
