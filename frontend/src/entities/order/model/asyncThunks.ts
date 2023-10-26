import { AxiosError } from "axios";
import { createAsyncThunk } from "@reduxjs/toolkit";

import { getOrders } from "../api/requests";

export const getOrdersAsync = createAsyncThunk(
    "user/login",
    async (_, { rejectWithValue }) => {
        try {
            const data = await getOrders();
            return data;
        } catch (err: unknown) {
            const axiosError = err as AxiosError<{ message: string }>;
            return rejectWithValue(axiosError.response?.data);
        }
    }
);
