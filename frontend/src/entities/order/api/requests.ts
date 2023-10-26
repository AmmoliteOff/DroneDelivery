import { api, config } from "shared/api";

export const getOrders = async () => {
    const { data } = await api.get(config.paths.order.get);
    return data.data;
};
