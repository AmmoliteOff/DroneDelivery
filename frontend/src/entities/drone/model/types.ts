export interface IDrone {
    id: string;
    img?: string;
    maxWeight: number;
    charging: number;
}

export interface DroneModel {
    drones: IDrone[];
    isLoading: boolean;
}
