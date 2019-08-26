import {Component, OnInit} from '@angular/core';
import {Product} from "../product/product";
import {ToastController} from "@ionic/angular";
import {CartService} from "../commons/cart.service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {BaseComponent} from "../commons/base.component";

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent extends BaseComponent implements OnInit {

    itemsInCart: Product[];

    constructor(private cartService: CartService, public toastController: ToastController) {
        super(toastController);
    }

    ngOnInit(): void {
        this.subscribeGetCartContent();
        this.getCartContent();
    }

    calculateTotal() {
        if (this.itemsInCart) {
            return this.itemsInCart.map(item => item.price).reduce((a, b) => a + b, 0);
        }
    }

    private subscribeGetCartContent() {
        this.cartService.getCart().pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(
            value => this.itemsInCart = value
        );
    }

    private getCartContent() {
        this.cartService.getCart();
    }
}
