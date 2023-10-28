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

export interface Order {
    id: string;
    customer: Customer;
    products: Product[];
    deliveryAdress: string;
    latitude: number;
    longitude: number;
}

export type ReqStatus = "CREATED" | "ACCEPTED" | "REJECTED";

// -------------------------->
export interface Requisition {
    status: ReqStatus;
    id: string;
    drone: IDrone;
    orders: Order[];
}
// <-------------------------

export interface OrderModel {
    requisitions: Requisition[];
    isLoading: boolean;
}
