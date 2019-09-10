import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppComponent} from '../app.component';
import {Product} from '../product/product';
import {Observable} from 'rxjs';

/**
 * @author: Petrovic Boban, boban_96@hotmail.de
 */
@Injectable()
export class ProductService {
    private productsUrl = AppComponent.REST_BASE_URL + '/products';

    constructor(private http: HttpClient) {
    }

    /**
     * Holt alle verfügbaren Produkte aus der Datenbank
     */
    getProducts() {
        return this.http.get<Product[]>(this.productsUrl);
    }

    /**
     * Holt ein Produkt aufgrund der productId
     */
    getProductById(productId: string): Observable<Product> {
        return this.http.get<Product>(this.productsUrl + '/' + productId);
    }
}
