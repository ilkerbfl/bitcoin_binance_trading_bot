import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { CoinWillBeListenedMySuffix } from './coin-will-be-listened-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CoinWillBeListenedMySuffixService {

    private resourceUrl = SERVER_API_URL + 'api/coin-will-be-listeneds';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/coin-will-be-listeneds';

    constructor(private http: Http) { }

    create(coinWillBeListened: CoinWillBeListenedMySuffix): Observable<CoinWillBeListenedMySuffix> {
        const copy = this.convert(coinWillBeListened);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(coinWillBeListened: CoinWillBeListenedMySuffix): Observable<CoinWillBeListenedMySuffix> {
        const copy = this.convert(coinWillBeListened);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<CoinWillBeListenedMySuffix> {
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
     * Convert a returned JSON object to CoinWillBeListenedMySuffix.
     */
    private convertItemFromServer(json: any): CoinWillBeListenedMySuffix {
        const entity: CoinWillBeListenedMySuffix = Object.assign(new CoinWillBeListenedMySuffix(), json);
        return entity;
    }

    /**
     * Convert a CoinWillBeListenedMySuffix to a JSON which can be sent to the server.
     */
    private convert(coinWillBeListened: CoinWillBeListenedMySuffix): CoinWillBeListenedMySuffix {
        const copy: CoinWillBeListenedMySuffix = Object.assign({}, coinWillBeListened);
        return copy;
    }
}
