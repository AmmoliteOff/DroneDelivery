import { api, config } from "shared/api";

export const getRequisitions = async () => {
    const { data } = await api.get(config.paths.reqs.get);
    return data;
};
