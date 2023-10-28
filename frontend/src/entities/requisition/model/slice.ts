import { createSlice } from "@reduxjs/toolkit";

import { type OrderModel } from "./types";
import { getRequisitionsAsync } from "./asyncThunks";

// const REQS: Requisition[] = [
//     {
//         id: 1,
//         drone: {
//             id: 1,
//             orders: [
//                 {
//                     id: 1,
//                     customerAddress: "45 стрелковой дивизии 259/7",
//                     customerName: "Егор",
//                     customerNumber: "89601082785",
//                     products: [
//                         {
//                             id: 1,
//                             weight: 3,
//                             name: "Яблоко",
//                             img: "https://res.cloudinary.com/do1tmxguz/image/upload/v1698485259/onpeb7suvzrpbrzh9edx.png",
//                         },
//                     ],
//                     longitude: 39.20667,
//                     latitude: 51.66646,
//                 },
//             ],
//             charge: 100,
//             maxWeight: 9,
//             currentLongitude: 39.20567,
//             currentLatitude: 51.65646,
//             imageLink:
//                 "https://yns1.ru/attachments/Image/dji-mavic-air.png?template=generic",
//         },
//         status: "CREATED",
//     },
//     {
//         id: 2,
//         drone: {
//             id: 2,
//             orders: [
//                 {
//                     id: 1,
//                     customerAddress: "45 стрелковой дивизии 259/7",
//                     customerName: "Егор",
//                     customerNumber: "89601082785",
//                     products: [
//                         {
//                             id: 1,
//                             weight: 3,
//                             name: "Яблоко",
//                             img: "https://res.cloudinary.com/do1tmxguz/image/upload/v1698485259/onpeb7suvzrpbrzh9edx.png",
//                         },
//                     ],
//                     longitude: 55.72,
//                     latitude: 37.44,
//                 },
//             ],
//             charge: 100,
//             maxWeight: 5,
//             currentLongitude: 39.20567,
//             currentLatitude: 51.65646,
//             imageLink:
//                 "https://yns1.ru/attachments/Image/dji-mavic-air.png?template=generic",
//         },
//         status: "CREATED",
//     },
// ];

const initialState: OrderModel = {
    requisitions: undefined,
    isLoading: false,
};

export const reqModel = createSlice({
    name: "req",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getRequisitionsAsync.pending, (state) => {
                state.isLoading = true;
            })
            .addCase(getRequisitionsAsync.fulfilled, (state, { payload }) => {
                state.isLoading = false;
                state.requisitions = payload;
            })
            .addCase(getRequisitionsAsync.rejected, (state) => {
                state.isLoading = false;
                // error
            });
    },
});
