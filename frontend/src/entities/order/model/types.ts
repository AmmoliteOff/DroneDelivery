export interface Product {
    id: string;
    img: string;
    name: string;
    weight: number;
}

export interface Drone {
    id: string;
    img?: string;
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
    drone: Drone;
    products: Product[];
    customer: Customer;
}
// <-------------------------

export interface OrderModel {
    orders: Order[];
    isLoading: boolean;
}
