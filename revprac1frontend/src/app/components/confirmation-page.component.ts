import { Component, OnInit } from '@angular/core';
import { Order } from '../models';
import { OrderService } from '../services/OrderService';

@Component({
  selector: 'app-confirmation-page',
  templateUrl: './confirmation-page.component.html',
  styleUrls: ['./confirmation-page.component.css']
})
export class ConfirmationPageComponent implements OnInit {

  order!: Order

  constructor(private orderSvc: OrderService) { }

  ngOnInit(): void {
    this.order = this.orderSvc.orderItem
  }

}
