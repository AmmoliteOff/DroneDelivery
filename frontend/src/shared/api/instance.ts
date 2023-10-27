import axios from "axios";

import { config } from "./config";

export const api = axios.create({
    baseURL: config.baseUrl,
});

api.interceptors.request.use((config) => {
    const accessToken = localStorage.getItem("accessToken");
    config.headers.Authorization = `Bearer ${accessToken}`;
    return config;
});
