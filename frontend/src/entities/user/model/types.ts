export interface User {
    id: string;
    login: string;
    name: string;
    surname: string;
}

export type UserModel = {
    isAuth: boolean;
    isLoading: boolean;
    user?: User;
};