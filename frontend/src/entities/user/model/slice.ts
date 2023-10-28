import { createSlice } from "@reduxjs/toolkit";

import { type UserModel } from "./types";
import { loginAsync } from "./asyncThunks";

const initialState: UserModel = {
    // user: {
    //     id: "1",
    //     name: "Иван",
    //     surname: "Иванов",
    //     userRole: "DRONES_SENDER",
    // },
    isAuth: false,
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
                state.isLoading = false;
                state.user = payload;
                state.isAuth = true;
            });
    },
});

export const { clearUserData } = userModel.actions;
