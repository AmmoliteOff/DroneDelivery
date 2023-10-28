import { AxiosError } from "axios";
import { createAsyncThunk } from "@reduxjs/toolkit";

import { getRequisitions } from "../api/requests";

export const getRequisitionssAsync = createAsyncThunk(
    "user/login",
    async (_, { rejectWithValue }) => {
        try {
            const data = await getRequisitions();
            return data;
        } catch (err: unknown) {
            const axiosError = err as AxiosError<{ message: string }>;
            return rejectWithValue(axiosError.response?.data);
        }
    }
);
