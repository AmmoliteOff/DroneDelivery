export interface Product {
    img: string;
    name: string;
    weight: number;
}

export interface Order {
    id: string;
    deliveryAdress: string;
    customerNumber: string;
    droneId: string;
    products: Product[];
}

export interface OrderModel {
    orders: Order[];
    isLoading: boolean;
}
