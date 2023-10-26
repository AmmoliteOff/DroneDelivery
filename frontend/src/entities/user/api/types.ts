import { User } from "../model/types";

export interface LoginProps {
    login: string;
    password: string;
}

export interface LoginResponse extends User {
    accessToken: string;
}
