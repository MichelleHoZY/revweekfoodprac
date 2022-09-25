import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { Order } from "../models";

const SERVICE_URL_SAVE_ORDER = "http://localhost:8080/order/newOrder"
const SERVICE_URL_RETRIEVE_ORDER = "http://localhost:8080/order/retrieve"
 
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
        // const headers = new HttpHeaders()
        //     .set('Content-Type', 'application/json')
        //     .set('Accept', 'application/json')

        // return firstValueFrom(
        //     this.http.post<Order>(SERVICE_URL_RETRIEVE_ORDER, orderId, {headers})
        // )

        return firstValueFrom(
            this.http.get<Order>(`http://localhost:8080/order/retrieve/${orderId}`)
        )
    }
}