import { User } from "../model/types";

export interface LoginProps {
    username: string;
    password: string;
}

export interface LoginResponse extends User {
    accessToken: string;
}
