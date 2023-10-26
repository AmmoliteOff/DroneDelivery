import { Routes, Route } from "react-router-dom";

import { Layout } from "shared/ui";
import { LoginPage } from "pages/login";
import { HomePage } from "pages/home/ui/HomePage";

export const Routing = () => {
    return (
        <Routes>
            <Route element={<Layout />}>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/home" element={<HomePage />} />
            </Route>
        </Routes>
    );
};
