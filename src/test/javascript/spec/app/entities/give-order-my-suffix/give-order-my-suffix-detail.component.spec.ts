/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BitcoinTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { GiveOrderMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/give-order-my-suffix/give-order-my-suffix-detail.component';
import { GiveOrderMySuffixService } from '../../../../../../main/webapp/app/entities/give-order-my-suffix/give-order-my-suffix.service';
import { GiveOrderMySuffix } from '../../../../../../main/webapp/app/entities/give-order-my-suffix/give-order-my-suffix.model';

describe('Component Tests', () => {

    describe('GiveOrderMySuffix Management Detail Component', () => {
        let comp: GiveOrderMySuffixDetailComponent;
        let fixture: ComponentFixture<GiveOrderMySuffixDetailComponent>;
        let service: GiveOrderMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BitcoinTestModule],
                declarations: [GiveOrderMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    GiveOrderMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(GiveOrderMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiveOrderMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiveOrderMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new GiveOrderMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.giveOrder).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
