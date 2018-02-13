/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BitcoinTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { StrategyOfCoinMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/strategy-of-coin-my-suffix/strategy-of-coin-my-suffix-detail.component';
import { StrategyOfCoinMySuffixService } from '../../../../../../main/webapp/app/entities/strategy-of-coin-my-suffix/strategy-of-coin-my-suffix.service';
import { StrategyOfCoinMySuffix } from '../../../../../../main/webapp/app/entities/strategy-of-coin-my-suffix/strategy-of-coin-my-suffix.model';

describe('Component Tests', () => {

    describe('StrategyOfCoinMySuffix Management Detail Component', () => {
        let comp: StrategyOfCoinMySuffixDetailComponent;
        let fixture: ComponentFixture<StrategyOfCoinMySuffixDetailComponent>;
        let service: StrategyOfCoinMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BitcoinTestModule],
                declarations: [StrategyOfCoinMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    StrategyOfCoinMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(StrategyOfCoinMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StrategyOfCoinMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StrategyOfCoinMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new StrategyOfCoinMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.strategyOfCoin).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
