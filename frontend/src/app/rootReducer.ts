import { combineReducers } from "@reduxjs/toolkit";

import { userModel } from "entities/user";
import { reqModel } from "entities/requisition";
import { droneModel } from "entities/drone/model/slice";

export const rootReducer = combineReducers({
    user: userModel.reducer,
    req: reqModel.reducer,
    drone: droneModel.reducer,
});
