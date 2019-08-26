import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from "../app.component";
import {Product} from "../product/product";
import {Observable} from "rxjs";

@Injectable()
export class ProductService {
    private productsUrl = AppComponent.REST_BASE_URL + '/products';

    constructor(private http: HttpClient) {
    }

    getProducts() {
        return this.http.get<Product[]>(this.productsUrl);
    }

    getProductById(productId: string): Observable<Product> {
        return this.http.get<Product>(this.productsUrl + '/' + productId);
    }
}
