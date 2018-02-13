import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { NewOrderLocaleMySuffix } from './new-order-locale-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class NewOrderLocaleMySuffixService {

    private resourceUrl = SERVER_API_URL + 'api/new-order-locales';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/new-order-locales';

    constructor(private http: Http) { }

    create(newOrderLocale: NewOrderLocaleMySuffix): Observable<NewOrderLocaleMySuffix> {
        const copy = this.convert(newOrderLocale);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(newOrderLocale: NewOrderLocaleMySuffix): Observable<NewOrderLocaleMySuffix> {
        const copy = this.convert(newOrderLocale);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<NewOrderLocaleMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to NewOrderLocaleMySuffix.
     */
    private convertItemFromServer(json: any): NewOrderLocaleMySuffix {
        const entity: NewOrderLocaleMySuffix = Object.assign(new NewOrderLocaleMySuffix(), json);
        return entity;
    }

    /**
     * Convert a NewOrderLocaleMySuffix to a JSON which can be sent to the server.
     */
    private convert(newOrderLocale: NewOrderLocaleMySuffix): NewOrderLocaleMySuffix {
        const copy: NewOrderLocaleMySuffix = Object.assign({}, newOrderLocale);
        return copy;
    }
}
