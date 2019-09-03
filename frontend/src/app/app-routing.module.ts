import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: '',
        redirectTo: 'overview',
        pathMatch: 'full'
    },
    {
        path: 'overview',
        loadChildren: './list/list.module#ListPageModule'
    },
    {
        path: 'productDetails/:productId',
        loadChildren: './product/product.module#ProductdetailsPageModule'
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules, useHash: true})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
