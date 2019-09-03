import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from "../app.component";
import {Cart} from "../cart/cart";

@Injectable()
export class CartService {
    private cartUrl = AppComponent.REST_BASE_URL + '/cart';

    constructor(private http: HttpClient) {
    }

    getCart() {
        return this.http.get<Cart[]>(this.cartUrl);
    }

    addToCart(productId: number): Promise<any> {
        return this.http.post(this.cartUrl, productId).toPromise();
    }

    checkOut(): Promise<any> {
        return this.http.get(this.cartUrl + '/checkout').toPromise();
    }
}
