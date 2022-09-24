import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from '../models';
import { OrderService } from '../services/OrderService';

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.css']
})
export class OrdersPageComponent implements OnInit {

  order: Order = {
    name: '',
    mobile: 0,
    time: '',
    orderId: '',
    orderItems: []
  }

  constructor(private orderSvc: OrderService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.order.orderId = this.activatedRoute.snapshot.params['orderId']
    this.orderSvc.retrieveOrder(this.order.orderId)
      .then(result => {
        console.log(`>>> Order: ${result}`)
        this.order = result
      })
      .catch(error => {
        console.log(`>>> Error: ${error}`)
      })
  }

}
