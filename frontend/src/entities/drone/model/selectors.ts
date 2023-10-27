import { createSelector } from "@reduxjs/toolkit";
import { IDrone } from ".";

export const dronesSelector = (state: RootState) => state.drone.drones;

export const dronesLoadingSelector = (state: RootState) =>
    state.drone.isLoading;

export const droneByIdSelector = createSelector(
    dronesSelector,
    (_: RootState, droneId: IDrone["id"] | undefined) => {
        return droneId;
    },
    (drones: IDrone[], droneId: IDrone["id"] | undefined) => {
        if (!droneId) return undefined;
        return drones.find((drone) => drone.id === droneId);
    }
);
