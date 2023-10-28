export type Role = "DRONES_SENDER" | "DRONES_MASTER";

export interface User {
    id: string;
    name: string;
    surname: string;
    role: Role;
}

export type UserModel = {
    isAuth: boolean;
    isLoading: boolean;
    user?: User;
};
