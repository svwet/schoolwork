import {Component, OnInit} from '@angular/core';
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {ModalController, NavController, ToastController} from "@ionic/angular";
import {Product} from "../commons/product";
import {ModalPage} from "./modal.page";
import {ProductService} from "../commons/product.service";

@Component({
    selector: 'app-list',
    templateUrl: 'list.page.html'
})
export class ListPage implements OnInit {
    items: Product[] = [];
    itemsInCart: Product[] = [];
    public products: Product[];

    constructor(public productService: ProductService, public toastController: ToastController, public modalController: ModalController, public navCtrl: NavController) {
    }

    async presentModal() {
        const modal = await this.modalController.create({
            component: ModalPage,
            componentProps: {
                'itemsInCart': this.itemsInCart
            }
        });
        return await modal.present();
    }


    ngOnInit() {
        this.subscribeGetProducts();
        this.productService.getProducts();
    }

    async presentToast(msg: string) {
        const toast = await this.toastController.create({
            message: msg,
            duration: 2000
        });
        await toast.present();
    }

    addToCart(item) {
        item.quantityInCart += 1;
        this.itemsInCart.push(item);
    }

    openProduct(product: Product) {
        this.addToCart(product);
        this.navCtrl.navigateRoot('/product/' + product.id);
    }

    private subscribeGetProducts() {
        this.productService.getProducts().pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(
            value => this.products = value
        );
    }
}
