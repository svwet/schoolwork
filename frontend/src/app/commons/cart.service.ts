import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from "../app.component";
import {Cart} from "../cart/cart";
import {Observable, of} from "rxjs";
import {BaseComponent} from "./base.component";
import {ToastController} from "@ionic/angular";
import {catchError} from "rxjs/operators";

@Injectable()
export class CartService extends BaseComponent{
    private cartUrl = AppComponent.REST_BASE_URL + '/cart';
    public addToCartObservable: Observable<void>;

    constructor(private http: HttpClient, protected toastController: ToastController) {
        super(toastController);
    }

    getCart() {
        return this.http.get<Cart[]>(this.cartUrl);
    }

    addToCart(productId: number) {
        this.http.post<void>(this.cartUrl, productId).pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Product with successfully added to the cart'));
    }

    checkOut() {
        this.http.get<void>(this.cartUrl + '/checkout').pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Checkout completed. We sent you a notification to your phone'));
    }

    dropProductFromCart(productId: number) {
        this.http.post<void>(this.cartUrl + '/dropProductFromCart', productId).pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Product successfully removed from the cart'))
    }
}
