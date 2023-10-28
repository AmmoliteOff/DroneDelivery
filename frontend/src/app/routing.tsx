import { Routes, Route, Navigate } from "react-router-dom";

import { useAppSelector } from "shared/model";
import { userSelector } from "entities/user";

import { Layout } from "shared/ui";

import { LoginPage } from "pages/login";
import { HomePage } from "pages/home";
import { DronesPage } from "pages/drones";
import { DronePage } from "pages/drone";
import { ProfilePage } from "pages/profile";

import { Header } from "widgets/header";
import { isAuthSelector } from "entities/user";

// interface GuardProps {
//     children: React.ReactNode;
// }
// const Guard = ({ children }: GuardProps) => {
//     const isAuthorized = useAppSelector(isAuthSelector);

//     if (!isAuthorized) {
//         return <Navigate to="/login" />;
//     }
//     return children;
// };

export const Routing = () => {
    return (
        <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route element={<Layout header={<Header />} />}>
                <Route path="/home" element={<HomePage />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/drones" element={<DronesPage />} />
                <Route path="/drones/:droneId" element={<DronePage />} />
            </Route>
        </Routes>
    );
};

// export const Routing = () => {
//     return (
//         <Routes>
//             <Route path="/login" element={<LoginPage />} />
//             <Route element={<Layout header={<Header />} />}>
//                 <Route
//                     path="/home"
//                     element={
//                         <Guard>
//                             <HomePage />
//                         </Guard>
//                     }
//                 />
//                 <Route
//                     path="/drones"
//                     element={
//                         <Guard>
//                             <DronesPage />
//                         </Guard>
//                     }
//                 />
//                 <Route
//                     path="/drones/:droneId"
//                     element={
//                         <Guard>
//                             <DronePage />
//                         </Guard>
//                     }
//                 />
//                 <Route
//                     path="/profile"
//                     element={
//                         <Guard>
//                             <ProfilePage />
//                         </Guard>
//                     }
//                 />
//             </Route>
//         </Routes>
//     );
// };
