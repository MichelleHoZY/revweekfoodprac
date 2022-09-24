export interface Order {
    name: string,
    mobile: number,
    time: string,
    orderId: string,
    orderItems: orderItem[]
}

export interface orderItem {
    item: string,
    quantity: string
}