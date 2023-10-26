import { createSlice } from "@reduxjs/toolkit";

import { OrderModel } from "./types";
import { getOrdersAsync } from "./asyncThunks";

const initialState: OrderModel = {
    orders: [],
    isLoading: false,
};

export const orderModel = createSlice({
    name: "order",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getOrdersAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(getOrdersAsync.fulfilled, (state, { payload }) => {
                state.isLoading = false;
                state.orders = payload;
            })
            .addCase(getOrdersAsync.rejected, (state) => {
                state.isLoading = false;
                // error
            });
    },
});
