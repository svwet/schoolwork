import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';

import {IonicModule} from '@ionic/angular';
import {ProductPage} from './product.page';
import {CommonsModule} from '../commons/commons.module';

const routes: Routes = [
    {
        path: '',
        component: ProductPage
    }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        CommonsModule,
        RouterModule.forChild(routes)
    ],
    declarations: [ProductPage]
})
export class ProductdetailsPageModule {
}
