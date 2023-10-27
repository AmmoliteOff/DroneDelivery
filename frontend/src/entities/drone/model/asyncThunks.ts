import { AxiosError } from "axios";
import { createAsyncThunk } from "@reduxjs/toolkit";

import { getDrones } from "../api/requests";

export const getDronesAsync = createAsyncThunk(
    "user/login",
    async (_, { rejectWithValue }) => {
        try {
            const data = await getDrones();
            return data;
        } catch (err: unknown) {
            const axiosError = err as AxiosError<{ message: string }>;
            return rejectWithValue(axiosError.response?.data);
        }
    }
);
