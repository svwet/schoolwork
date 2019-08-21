import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../commons/product.service";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {Product} from "../commons/product";
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-productdetails',
  templateUrl: './product.page.html',
  styleUrls: ['./product.page.scss'],
})
export class ProductPage implements OnInit {
  public productId: string;
  public product: Product;

  constructor(private activatedRoute: ActivatedRoute,  public toastController: ToastController, private productService: ProductService) { }

  ngOnInit() {
    this.productId = this.activatedRoute.snapshot.paramMap.get('productId');
    this.subscribeGetProductById();
    this.productService.getProductById(this.productId);
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

  async presentToast(msg: string) {
    const toast = await this.toastController.create({
      message: msg,
      duration: 2000
    });
    await toast.present();
  }
}

