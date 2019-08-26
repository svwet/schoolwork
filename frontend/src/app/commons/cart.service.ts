import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from "../app.component";
import {Product} from "../product/product";
import {Observable} from "rxjs";

@Injectable()
export class CartService {
    private cartUrl = AppComponent.REST_BASE_URL + '/cart';

    constructor(private http: HttpClient) {
    }

    getCart() {
        return this.http.get<Product[]>(this.cartUrl);
    }

    addToCart(productId: string): Observable<Product> {
        return this.http.get<Product>(this.cartUrl + '/' + productId);
    }
}
