interface ConfigAPI {
    baseUrl: string;
    paths: {
        user: {
            login: string;
            logout: string;
        };
        reqs: {
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
        reqs: {
            get: "/api/requests",
        },
        drones: {
            get: "/api/drones",
        },
    },
};
