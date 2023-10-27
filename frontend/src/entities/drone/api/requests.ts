import { api, config } from "shared/api";

import { IDrone } from "../model";

export const getDrones = async () => {
    const { data } = await api.get<IDrone[]>(config.paths.drones.get);
    return data;
};
