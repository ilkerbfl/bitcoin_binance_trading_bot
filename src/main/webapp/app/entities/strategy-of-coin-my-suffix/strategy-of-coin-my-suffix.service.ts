import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { StrategyOfCoinMySuffix } from './strategy-of-coin-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class StrategyOfCoinMySuffixService {

    private resourceUrl = SERVER_API_URL + 'api/strategy-of-coins';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/strategy-of-coins';

    constructor(private http: Http) { }

    create(strategyOfCoin: StrategyOfCoinMySuffix): Observable<StrategyOfCoinMySuffix> {
        const copy = this.convert(strategyOfCoin);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(strategyOfCoin: StrategyOfCoinMySuffix): Observable<StrategyOfCoinMySuffix> {
        const copy = this.convert(strategyOfCoin);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<StrategyOfCoinMySuffix> {
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
     * Convert a returned JSON object to StrategyOfCoinMySuffix.
     */
    private convertItemFromServer(json: any): StrategyOfCoinMySuffix {
        const entity: StrategyOfCoinMySuffix = Object.assign(new StrategyOfCoinMySuffix(), json);
        return entity;
    }

    /**
     * Convert a StrategyOfCoinMySuffix to a JSON which can be sent to the server.
     */
    private convert(strategyOfCoin: StrategyOfCoinMySuffix): StrategyOfCoinMySuffix {
        const copy: StrategyOfCoinMySuffix = Object.assign({}, strategyOfCoin);
        return copy;
    }
}
