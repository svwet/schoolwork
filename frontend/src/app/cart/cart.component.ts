import {Component, OnInit} from '@angular/core';
import {ToastController} from "@ionic/angular";
import {CartService} from "../commons/cart.service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {BaseComponent} from "../commons/base.component";
import {Cart} from "./cart";

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent extends BaseComponent implements OnInit {

    itemsInCart: Cart[];

    constructor(private cartService: CartService, public toastController: ToastController) {
        super(toastController);
    }

    ngOnInit(): void {
        this.cartService.getCart().pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(
            value => this.itemsInCart = value
        );
        this.getCartContent();
    }

    calculateTotal() {
        if (this.itemsInCart) {
            return this.itemsInCart.reduce((a, b) => a + (b.count * b.product.price), 0)
        }
    }

    calculateTotalOfRow(item: Cart) {
        return item.count * item.product.price;
    }

    private getCartContent() {
        this.cartService.getCart();
    }

    checkout() {
        this.cartService.checkOut();
    }

    clean() {
        console.log('not yet implemented....')
    }
}
