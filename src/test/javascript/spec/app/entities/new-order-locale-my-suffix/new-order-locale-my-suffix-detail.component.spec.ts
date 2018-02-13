/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BitcoinTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { NewOrderLocaleMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/new-order-locale-my-suffix/new-order-locale-my-suffix-detail.component';
import { NewOrderLocaleMySuffixService } from '../../../../../../main/webapp/app/entities/new-order-locale-my-suffix/new-order-locale-my-suffix.service';
import { NewOrderLocaleMySuffix } from '../../../../../../main/webapp/app/entities/new-order-locale-my-suffix/new-order-locale-my-suffix.model';

describe('Component Tests', () => {

    describe('NewOrderLocaleMySuffix Management Detail Component', () => {
        let comp: NewOrderLocaleMySuffixDetailComponent;
        let fixture: ComponentFixture<NewOrderLocaleMySuffixDetailComponent>;
        let service: NewOrderLocaleMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BitcoinTestModule],
                declarations: [NewOrderLocaleMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    NewOrderLocaleMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(NewOrderLocaleMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NewOrderLocaleMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NewOrderLocaleMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new NewOrderLocaleMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.newOrderLocale).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
