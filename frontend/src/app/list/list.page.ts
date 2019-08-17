import {Component, OnInit} from '@angular/core';
import {ListService} from "./list.service";
import {User} from "./user";
import {catchError} from "rxjs/operators";
import {of} from "rxjs";
import {ModalController, NavController, ToastController} from "@ionic/angular";
import {Product} from "./product";
import {ModalPage} from "./modal.page";

@Component({
    selector: 'app-list',
    templateUrl: 'list.page.html',
    styleUrls: ['list.page.scss']
})
export class ListPage implements OnInit {
    items: Product[] = [];
    itemsInCart: Product[] = [];

    private users: User[];
    public products: Product[] = [{id: 1, description: "test", name: "macbook", price: 23.25}, {
        id: 1,
        description: "test",
        name: "hp envy",
        price: 255.95
    }, {id: 1, description: "test", name: "macbook", price: 23.25}, {
        id: 1,
        description: "test",
        name: "asdf",
        price: 23.25
    }, {id: 1, description: "test", name: "macbook", price: 23.25}, {
        id: 1,
        description: "test",
        name: "ssss",
        price: 23.25
    }, {id: 3323, description: "test", name: "macbook", price: 23.25}, {
        id: 123,
        description: "test",
        name: "fffdsfdf",
        price: 23.25
    }, {id: 99, description: "test", name: "macbook", price: 23.25}, {
        id: 2,
        description: "test",
        name: "dslkfjs",
        price: 23.25
    }];


    constructor(public listService: ListService, public toastController: ToastController, public modalController: ModalController, public navCtrl: NavController) {
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
        this.listService.getGoodBeers().pipe(
            catchError(err => {
                this.presentToast(err);
                return of([]);
            })
        ).subscribe(
            value => this.users = value
        );
        this.listService.getGoodBeers();
    }

    async presentToast(msg: string) {
        const toast = await this.toastController.create({
            message: msg,
            duration: 2000
        });
        await toast.present();
    }

    addToCart(item){
        item.quantityInCart += 1;
        this.itemsInCart.push(item);
    }

    openProduct(product: Product) {
        this.addToCart(product);
        this.navCtrl.navigateRoot('/product/'+product.id);

    }
}
