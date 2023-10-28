import { api, config } from "shared/api";

export const getRequisitions = async () => {
    const { data } = await api.get(config.paths.reqs.get);
    console.log(data);
    return data;
};
