import { Routes, Route } from "react-router-dom";

import { Layout } from "shared/ui";
import { LoginPage } from "pages/login";
import { HomePage } from "pages/home";
import { DronesPage } from "pages/drones";
import { DronePage } from "pages/drone";
import { Header } from "widgets/header";

export const Routing = () => {
    return (
        <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route element={<Layout header={<Header />} />}>
                <Route path="/home" element={<HomePage />} />
                <Route path="/drones" element={<DronesPage />} />
                <Route path="/drones/:droneId" element={<DronePage />} />
            </Route>
        </Routes>
    );
};
