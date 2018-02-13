/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BitcoinTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CoinWillBeListenedMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/coin-will-be-listened-my-suffix/coin-will-be-listened-my-suffix-detail.component';
import { CoinWillBeListenedMySuffixService } from '../../../../../../main/webapp/app/entities/coin-will-be-listened-my-suffix/coin-will-be-listened-my-suffix.service';
import { CoinWillBeListenedMySuffix } from '../../../../../../main/webapp/app/entities/coin-will-be-listened-my-suffix/coin-will-be-listened-my-suffix.model';

describe('Component Tests', () => {

    describe('CoinWillBeListenedMySuffix Management Detail Component', () => {
        let comp: CoinWillBeListenedMySuffixDetailComponent;
        let fixture: ComponentFixture<CoinWillBeListenedMySuffixDetailComponent>;
        let service: CoinWillBeListenedMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BitcoinTestModule],
                declarations: [CoinWillBeListenedMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CoinWillBeListenedMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(CoinWillBeListenedMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CoinWillBeListenedMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoinWillBeListenedMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CoinWillBeListenedMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.coinWillBeListened).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
