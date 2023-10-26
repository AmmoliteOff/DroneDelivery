import { createAsyncThunk } from "@reduxjs/toolkit";
import { type LoginProps, type LoginResponse } from "../api/types";

import { login, logout } from "../api/requests";
import { AxiosError } from "axios";

export const loginAsync = createAsyncThunk<LoginResponse, LoginProps>(
    "user/login",
    async (props: LoginProps, { rejectWithValue }) => {
        try {
            const data = await login(props);
            return data;
        } catch (err: unknown) {
            const axiosError = err as AxiosError<{ message: string }>;
            return rejectWithValue(axiosError.response?.data);
        }
    }
);

export const logoutAsync = createAsyncThunk("user/logout", () => {
    return logout();
});
