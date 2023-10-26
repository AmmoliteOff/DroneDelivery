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
    };
}

export const config: ConfigAPI = {
    baseUrl: "http://localhost:8080/",
    paths: {
        user: {
            login: "/user/login",
            logout: "/user/logout",
        },
        order: {
            get: "/orders",
        },
    },
};
