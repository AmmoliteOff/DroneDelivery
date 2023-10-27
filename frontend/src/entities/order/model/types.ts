import { IDrone } from "entities/drone/@x/order";

export interface Product {
    id: string;
    img: string;
    name: string;
    weight: number;
}

export interface Customer {
    name: string;
    surname: string;
    telephoneNumber: string;
}

// -------------------------->
export interface Order {
    id: string;
    deliveryAdress: string;
    drone: IDrone;
    products: Product[];
    customer: Customer;
}
// <-------------------------

export interface OrderModel {
    orders: Order[];
    isLoading: boolean;
}
