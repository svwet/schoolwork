import {Component, Input} from '@angular/core';
import {Product} from "./product";

@Component({
    templateUrl: 'modal.page.html'
})
export class ModalPage {

    @Input() itemsInCart: Product[];

    calculateTotal() {
        return this.itemsInCart.map(item => item.price).reduce((a, b) => a + b, 0);
    }
}
