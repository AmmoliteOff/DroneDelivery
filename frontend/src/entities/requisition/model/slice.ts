import { createSlice } from "@reduxjs/toolkit";

import { type OrderModel, type Requisition } from "./types";
import { getRequisitionssAsync } from "./asyncThunks";

const REQS: Requisition[] = [
    {
        id: "864",
        orders: [
            {
                deliveryAdress: "Кольцовская д. 1",
                latitude: 55.72,
                longitude: 37.44,
                id: "951",
                customer: {
                    name: "Ваня",
                    surname: "Иванов",
                    telephoneNumber: "8594595943",
                },
                products: [
                    { id: "1", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "2", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
            {
                deliveryAdress: "Кольцовская д. 1",
                latitude: 55.8,
                longitude: 37.4,
                id: "32",
                customer: {
                    name: "Ваня",
                    surname: "Иванов",
                    telephoneNumber: "8594595943",
                },
                products: [
                    { id: "3", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "4", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
        ],
        drone: {
            id: "12",
            img: "drone.png",
            maxWeight: 10,
            charging: 90,
            latitude: 55.76,
            longitude: 37.64,
        },
    },
    {
        id: "432",
        orders: [
            {
                id: "35",
                latitude: 55.72,
                longitude: 37.44,
                deliveryAdress: "Университетская д. 5",
                customer: {
                    name: "Сергей",
                    surname: "Сергеев",
                    telephoneNumber: "88005553535",
                },
                products: [
                    { id: "5", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "6", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
        ],
        drone: {
            id: "321",
            img: "drone.png",
            maxWeight: 4,
            charging: 70,
            latitude: 55.76,
            longitude: 37.64,
        },
    },
];

const initialState: OrderModel = {
    requisitions: REQS,
    isLoading: false,
};

export const reqModel = createSlice({
    name: "req",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getRequisitionssAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(getRequisitionssAsync.fulfilled, (state, { payload }) => {
                state.isLoading = false;
                state.requisitions = payload;
            })
            .addCase(getRequisitionssAsync.rejected, (state) => {
                state.isLoading = false;
                // error
            });
    },
});
