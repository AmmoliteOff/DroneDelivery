import { type LoginProps, type LoginResponse } from "./types";
import { config, api } from "shared/api";

export const login = async (props: LoginProps) => {
    const response = await api.post<LoginResponse>(
        config.paths.user.login,
        props
    );

    const accessToken: string = response.headers["Authorization"];
    return { ...response.data, accessToken };
};

export const logout = async () => {
    const { data } = await api.post(config.paths.user.logout);
    return data.data;
};
