import { combineReducers } from "@reduxjs/toolkit";

import { userModel } from "entities/user";
import { orderModel } from "entities/order";
import { droneModel } from "entities/drone/model/slice";

export const rootReducer = combineReducers({
    user: userModel.reducer,
    order: orderModel.reducer,
    drone: droneModel.reducer,
});
