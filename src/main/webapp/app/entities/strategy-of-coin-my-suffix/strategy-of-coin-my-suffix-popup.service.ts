import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { StrategyOfCoinMySuffix } from './strategy-of-coin-my-suffix.model';
import { StrategyOfCoinMySuffixService } from './strategy-of-coin-my-suffix.service';

@Injectable()
export class StrategyOfCoinMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private strategyOfCoinService: StrategyOfCoinMySuffixService

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
                this.strategyOfCoinService.find(id).subscribe((strategyOfCoin) => {
                    this.ngbModalRef = this.strategyOfCoinModalRef(component, strategyOfCoin);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.strategyOfCoinModalRef(component, new StrategyOfCoinMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    strategyOfCoinModalRef(component: Component, strategyOfCoin: StrategyOfCoinMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.strategyOfCoin = strategyOfCoin;
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
