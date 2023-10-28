import { type Order } from "../../requisition/model/types";

export interface IDrone {
    id: number;
    img?: string;

    orders: Order[];

    imageLink: string;

    charge: number;
    maxWeight: number;

    currentLatitude: number;
    currentLongitude: number;
}

export interface DroneModel {
    drones: IDrone[];
    isLoading: boolean;
}
