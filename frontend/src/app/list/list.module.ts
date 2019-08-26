import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {IonicModule} from '@ionic/angular';
import {RouterModule} from '@angular/router';

import {ListPage} from './list.page';
import {CommonsModule} from "../commons/commons.module";
import {CartComponent} from "../cart/cart.component";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        CommonsModule,
        RouterModule.forChild([
            {
                path: '',
                component: ListPage
            }
        ])
    ], entryComponents: [
        CartComponent,
    ],
    declarations: [ListPage, CartComponent]
})
export class ListPageModule {
}
