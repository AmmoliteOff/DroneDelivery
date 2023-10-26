import { combineReducers } from "@reduxjs/toolkit";

import { userModel } from "entities/user";

export const rootReducer = combineReducers({
    user: userModel.reducer,
});
