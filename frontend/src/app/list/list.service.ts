import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ListService {
    public API = 'http://127.0.0.1:8080';
    public BEER_API = this.API + '/users';

    constructor(public http: HttpClient) {
    }

    getGoodBeers(): Observable<any> {
        return this.http.get(this.BEER_API);
    }
}
