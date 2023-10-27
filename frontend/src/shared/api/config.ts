interface ConfigAPI {
    baseUrl: string;
    paths: {
        user: {
            login: string;
            logout: string;
        };
        order: {
            get: string;
        };

        drones: {
            get: string;
        };
    };
}

export const config: ConfigAPI = {
    baseUrl: "http://localhost:8765/",
    paths: {
        user: {
            login: "/api/authenticate",
            logout: "/api/logout",
        },
        order: {
            get: "/api/orders",
        },
        drones: {
            get: "/api/drones",
        },
    },
};
