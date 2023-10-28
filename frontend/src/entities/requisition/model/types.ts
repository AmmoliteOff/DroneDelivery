import { IDrone } from "entities/drone/@x/order";

export interface Product {
    id: number;
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
    id: number;

    customerAddress: string;
    customerName: string;
    customerNumber: string;

    products: Product[];

    latitude: number;
    longitude: number;
}

export type ReqStatus = "CREATED" | "ACCEPTED" | "REJECTED";

// -------------------------->
export interface Requisition {
    id: number;
    drone: IDrone;
    status: ReqStatus;
}
// <-------------------------

export interface OrderModel {
    requisitions: Requisition[] | undefined;
    isLoading: boolean;
}
