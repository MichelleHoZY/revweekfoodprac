import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { OrderFormComponent } from './components/order-form.component';
import { ConfirmationPageComponent } from './components/confirmation-page.component';
import { OrdersPageComponent } from './components/orders-page.component';

import {Router, RouterModule, Routes} from '@angular/router'
import { OrderService } from './services/OrderService';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'

const appRoutes: Routes = [
  {path: '', component: OrderFormComponent},
  {path: 'confirm', component: ConfirmationPageComponent},
  {path: 'check/:orderId', component: OrdersPageComponent},
  {path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    OrderFormComponent,
    ConfirmationPageComponent,
    OrdersPageComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes), HttpClientModule
  ],
  providers: [OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
