import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductService} from './product.service';
import {CartService} from './cart.service';

@NgModule({
    declarations: [],
    imports: [
        CommonModule
    ],
    providers: [ProductService, CartService],
})
export class CommonsModule {
}
