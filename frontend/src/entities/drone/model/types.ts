export interface IDrone {
    id: string;
    img?: string;
    maxWeight: number;
    charging: number;

    latitude: number;
    longitude: number;
}

export interface DroneModel {
    drones: IDrone[];
    isLoading: boolean;
}
