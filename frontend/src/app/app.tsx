import "../shared/base.css";

import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

import { Routing } from "./routing";
import { store } from "./store";

const root = document.getElementById("root") as HTMLElement;

ReactDOM.createRoot(root).render(
    <React.StrictMode>
        <BrowserRouter>
            <Provider store={store}>
                <Routing />
            </Provider>
        </BrowserRouter>
    </React.StrictMode>
);
