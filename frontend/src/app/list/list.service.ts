import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "./user";

@Injectable()
export class ListService {
    public static readonly API = 'http://127.0.0.1:8080';
    public BEER_API = ListService.API + '/users';

    constructor(private http: HttpClient) {
    }

    getGoodBeers() {
        return this.http.get<User[]>(this.BEER_API);
    }
}
