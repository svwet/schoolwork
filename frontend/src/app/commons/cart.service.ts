import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Cart} from '../cart/cart';
import {Observable, of} from 'rxjs';
import {BaseComponent} from './base.component';
import {ToastController} from '@ionic/angular';
import {catchError} from 'rxjs/operators';

/**
 * @author: Petrovic Boban, boban_96@hotmail.de
 */
@Injectable()
export class CartService extends BaseComponent {
    private cartUrl = AppComponent.REST_BASE_URL + '/cart';
    public addToCartObservable: Observable<void>;

    constructor(private http: HttpClient, protected toastController: ToastController) {
        super(toastController);
    }

    /**
     * Holt den Warenkorb des aktuellen Benutzers
     */
    getCart() {
        return this.http.get<Cart[]>(this.cartUrl);
    }

    /**
     * Fügt ein Produkt zum Warenkorb hinzu.
     */
    addToCart(productId: number) {
        this.http.post<void>(this.cartUrl, productId).pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Produkt erfolgreich zum Warenkorb hinzugefügt'));
    }

    /**
     * Vollendung des Kaufvorgangs
     */
    checkOut() {
        this.http.get<void>(this.cartUrl + '/checkout').pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Kaufvorgang abgeschlossen. In kürze werden sie eine SMS von uns erhalten'));
    }

    /**
     * Löscht ein Produkt aus dem Warenkorb
     */
    dropProductFromCart(productId: number) {
        this.http.post<void>(this.cartUrl + '/dropProductFromCart', productId).pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(value => this.presentToast('Produkt aus dem Warenkorb gelöscht'));
    }
}
