import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {IonicModule} from '@ionic/angular';
import {RouterModule} from '@angular/router';

import {ListPage} from './list.page';
import {ListService} from "./list.service";
import {ModalPage} from "./modal.page";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild([
      {
        path: '',
        component: ListPage
      }
    ])
  ], entryComponents: [
    ModalPage,
  ],
  providers:[ListService],
  declarations: [ListPage, ModalPage]
})
export class ListPageModule {}
