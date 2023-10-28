import { createSlice } from "@reduxjs/toolkit";

import { type UserModel } from "./types";
import { loginAsync, logoutAsync } from "./asyncThunks";

const initialState: UserModel = {
    user: {
        id: "1",
        name: "Иван",
        surname: "Иванов",
        role: "DRONES_SENDER",
    },
    isAuth: true,
    isLoading: false,
};

export const userModel = createSlice({
    name: "user",
    initialState,
    reducers: {
        clearUserData: (state) => {
            state.isAuth = false;
            state.isLoading = false;
            state.user = undefined;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(loginAsync.rejected, (state) => {
                state.isLoading = false;
            })
            .addCase(loginAsync.fulfilled, (state, { payload }) => {
                const { accessToken, ...user } = payload;
                localStorage.setItem("accessToken", accessToken);
                state.isLoading = false;
                state.user = user;
                state.isAuth = true;
            })

            .addCase(logoutAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(logoutAsync.rejected, (state) => {
                state.isLoading = false;
            })
            .addCase(logoutAsync.fulfilled, (state) => {
                state.isAuth = false;
                state.isLoading = false;
                state.user = undefined;
                localStorage.removeItem("accessToken");
            });
    },
});

export const { clearUserData } = userModel.actions;
