import { createSlice } from "@reduxjs/toolkit";

import { DroneModel, IDrone } from "./types";
import { getDronesAsync } from "./asyncThunks";

const DRONE_IMAGE_URL =
    "https://res.cloudinary.com/do1tmxguz/image/upload/v1698407549/vkcgft6haenthmvmxxv2.png";

const DRONES: IDrone[] = [
    {
        id: "1",
        img: DRONE_IMAGE_URL,
        maxWeight: 1,
        charging: 20,
    },
    {
        id: "2",
        img: DRONE_IMAGE_URL,
        maxWeight: 20,
        charging: 70,
    },
    {
        id: "3",
        img: DRONE_IMAGE_URL,
        maxWeight: 13,
        charging: 90,
    },
    {
        id: "4",
        img: DRONE_IMAGE_URL,
        maxWeight: 8,
        charging: 100,
    },
    {
        id: "5",
        img: DRONE_IMAGE_URL,
        maxWeight: 7,
        charging: 0,
    },
    {
        id: "6",
        img: DRONE_IMAGE_URL,
        maxWeight: 9,
        charging: 25,
    },
    {
        id: "7",
        img: DRONE_IMAGE_URL,
        maxWeight: 30,
        charging: 50,
    },
    {
        id: "8",
        img: DRONE_IMAGE_URL,
        maxWeight: 4,
        charging: 65,
    },
];

const initialState: DroneModel = {
    drones: DRONES,
    isLoading: false,
};

export const droneModel = createSlice({
    name: "drone",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getDronesAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(getDronesAsync.fulfilled, (state, { payload }) => {
                state.isLoading = false;
                state.drones = payload;
            })
            .addCase(getDronesAsync.rejected, (state) => {
                state.isLoading = false;
            });
    },
});
