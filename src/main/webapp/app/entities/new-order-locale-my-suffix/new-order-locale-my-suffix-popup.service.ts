import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NewOrderLocaleMySuffix } from './new-order-locale-my-suffix.model';
import { NewOrderLocaleMySuffixService } from './new-order-locale-my-suffix.service';

@Injectable()
export class NewOrderLocaleMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private newOrderLocaleService: NewOrderLocaleMySuffixService

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
                this.newOrderLocaleService.find(id).subscribe((newOrderLocale) => {
                    this.ngbModalRef = this.newOrderLocaleModalRef(component, newOrderLocale);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.newOrderLocaleModalRef(component, new NewOrderLocaleMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    newOrderLocaleModalRef(component: Component, newOrderLocale: NewOrderLocaleMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.newOrderLocale = newOrderLocale;
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
