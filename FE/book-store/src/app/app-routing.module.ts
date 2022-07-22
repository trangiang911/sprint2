import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {DetailComponent} from "./detail/detail.component";
import {CartComponent} from "./cart/cart.component";


const routes: Routes = [
  {path:'',pathMatch:'full',redirectTo:'home'},
  {path:'home',component: HomeComponent},
  {path:'detail/:id',component: DetailComponent},
  {path:'cart',component: CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
