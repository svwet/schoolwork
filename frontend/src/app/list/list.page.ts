import {Component, OnInit} from '@angular/core';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs';
import {ModalController, NavController, ToastController} from '@ionic/angular';
import {Product} from '../product/product';
import {ProductService} from '../commons/product.service';
import {CartComponent} from '../cart/cart.component';
import {BaseComponent} from '../commons/base.component';
import {Cart} from '../cart/cart';

@Component({
    selector: 'app-list',
    templateUrl: 'list.page.html'
})
/**
 * @author: Petrovic Boban, boban_96@hotmail.de
 */
export class ListPage extends BaseComponent implements OnInit {
    items: Product[] = [];
    itemsInCart: Cart[];
    public products: Product[];
    public searchBarValue: string;
    public filteredProducts: Product[];

    constructor(public productService: ProductService, public modalController: ModalController,
                public navCtrl: NavController, protected toastController: ToastController) {
        super(toastController);
    }

    ngOnInit() {
        this.subscribeGetProducts();
        this.productService.getProducts();
    }

    openProduct(product: Product) {
        this.navCtrl.navigateRoot('/productDetails/' + product.productId);
    }

    async presentCartModal() {
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
            value => {
                this.products = value;
                this.filteredProducts = value;
            }
        );
    }

    /**
     * Filtert die Produktliste aufgrund der Eingabe im Suchfeld.
     */
    filterItems() {
        if (this.products) {
            this.filteredProducts = this.products.filter(product => {
                return product.name.toLowerCase().includes(this.searchBarValue)
                    || product.shortDescription.toLowerCase().includes(this.searchBarValue)
                    || product.longDescription.toLowerCase().includes(this.searchBarValue);
            });
        }
    }

    /**
     * Setzt die gefilterte Produktliste zurück
     */
    reset() {
        this.filteredProducts = this.products;
    }
}
