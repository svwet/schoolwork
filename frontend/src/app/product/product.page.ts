import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../commons/product.service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {Product} from "./product";
import {ToastController} from "@ionic/angular";
import {BaseComponent} from "../commons/base.component";
import {CartService} from "../commons/cart.service";

@Component({
    selector: 'app-productdetails',
    templateUrl: './product.page.html',
    styleUrls: ['./product.page.scss'],
})
export class ProductPage extends BaseComponent implements OnInit {
    public productId: string;
    public product: Product;

    constructor(private activatedRoute: ActivatedRoute, protected toastController: ToastController, private productService: ProductService, private cartService: CartService) {
        super(toastController)
    }

    ngOnInit() {
        this.productId = this.activatedRoute.snapshot.paramMap.get('productId');
        this.subscribeGetProductById();
        this.productService.getProductById(this.productId);
    }

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
}

