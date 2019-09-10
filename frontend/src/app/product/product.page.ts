import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../commons/product.service';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs';
import {Product} from './product';
import {ToastController} from '@ionic/angular';
import {BaseComponent} from '../commons/base.component';
import {CartService} from '../commons/cart.service';

@Component({
    selector: 'app-productdetails',
    templateUrl: './product.page.html',
    styleUrls: ['./product.page.scss'],
})
/**
 * @author: Petrovic Boban, boban_96@hotmail.de
 */
export class ProductPage extends BaseComponent implements OnInit {
    public productId: string;
    public product: Product;
    public rating: number;

    constructor(private activatedRoute: ActivatedRoute, protected toastController: ToastController,
                private productService: ProductService, private cartService: CartService) {
        super(toastController);
    }

    ngOnInit() {
        this.productId = this.activatedRoute.snapshot.paramMap.get('productId');
        this.subscribeGetProductById();
        this.subscribeGetProductById();
        this.productService.getProductById(this.productId);
        this.setRandomRating();
    }

    /**
     * siehe Beschreibung im cartService
     */
    addToCart(productId: number) {
        this.cartService.addToCart(productId);
    }

    private subscribeGetProductById() {
        this.productService.getProductById(this.productId).pipe(
            catchError(err => {
                this.presentToast(err.error.message);
                return of<Product>();
            })
        ).subscribe(
            value => this.product = value
        );
    }

    /**
     * Es wird ein dynamisches Array aufgebaut mit den URL's der Produktfotos
     */
    getImagesByProductId() {
        return ['/' + this.productId + '_' + '1.jpg', '/' + this.productId + '_' + '2.jpg', '/' + this.productId + '_' + '3.jpg'];
    }

    setRandomRating() {
        this.rating = Math.floor(Math.random() * 999);
    }

}

