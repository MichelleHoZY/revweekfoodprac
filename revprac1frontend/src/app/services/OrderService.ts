import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { Order } from "../models";

const SERVICE_URL_SAVE_ORDER = "http://localhost:8080/order/newOrder"
 
@Injectable()
export class OrderService {
    orderItem!: Order

    constructor(private http: HttpClient) {}

    saveOrder(order: Order): Promise<string> {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')

        return lastValueFrom(
            this.http.post<string>(SERVICE_URL_SAVE_ORDER, order, {headers})
        )
    }

    retrieveOrder(orderId: string): Promise<Order> {
        return firstValueFrom(
            this.http.get<Order>(`http://localhost:8080/order/retrieve/${orderId}`)
        )
    }
}