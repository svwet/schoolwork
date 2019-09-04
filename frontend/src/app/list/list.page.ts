import {Component, OnInit} from '@angular/core';
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {ModalController, NavController, ToastController} from "@ionic/angular";
import {Product} from "../product/product";
import {ProductService} from "../commons/product.service";
import {CartComponent} from "../cart/cart.component";
import {BaseComponent} from "../commons/base.component";
import {Cart} from "../cart/cart";

@Component({
    selector: 'app-list',
    templateUrl: 'list.page.html'
})
export class ListPage extends BaseComponent implements OnInit {
    items: Product[] = [];
    itemsInCart: Cart[];
    public products: Product[];

    constructor(public productService: ProductService, public modalController: ModalController, public navCtrl: NavController, protected toastController: ToastController) {
        super(toastController);
    }

    ngOnInit() {
        this.subscribeGetProducts();
        this.productService.getProducts();
    }

    openProduct(product: Product) {
        this.navCtrl.navigateRoot('/productDetails/' + product.productId);
    }

    async presentModal() {
        const modal = await this.modalController.create({
            component: CartComponent,
        });
        return await modal.present();
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
