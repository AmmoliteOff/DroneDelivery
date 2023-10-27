import { Outlet } from "react-router-dom";

import css from "./Layout.module.css";

interface LayoutProps {
    header?: React.ReactNode;
}

export const Layout: React.FC<LayoutProps> = ({ header }) => {
    return (
        <div className={css.root}>
            {header && header}
            <Outlet />
        </div>
    );
};
