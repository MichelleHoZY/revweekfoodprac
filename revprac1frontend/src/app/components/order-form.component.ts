import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Order, orderItem } from '../models';
import { OrderService } from '../services/OrderService';
import {Router} from '@angular/router'
import {v4 as uuid} from 'uuid'

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {

  form!: FormGroup
  orderArr!: FormArray

  constructor(private fb: FormBuilder, private orderSvc: OrderService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    this.orderArr = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>(''),
      mobile: this.fb.control<number>(0),
      time: this.fb.control<string>('lunch'),
      orderItems: this.orderArr
    })
  }

  add() {
    const items = this.fb.group({
      item: this.fb.control<string>(''),
      quantity: this.fb.control<string>('1')
    })
    this.orderArr.push(items)
  }

  processForm() {
    const order: Order = this.form.value as Order
    order.orderId = uuid().substring(0,8)
    this.orderSvc.orderItem = order
    console.log(`>>> Order name: ${order.name} order mobile: ${order.mobile} order pickup time: ${order.time} order ID: ${order.orderId} order items: ${order.orderItems.length}`)
    console.log(`>>> Service: ${this.orderSvc.orderItem.orderId}`)

    this.orderSvc.saveOrder(order)
      .then(result => {
        console.log(`>>> Result: ${result}`)
      })
      .catch(error => {
        console.log(`>>> Error: ${error}`)
      })

      this.router.navigate(['confirm'])
  }

}
