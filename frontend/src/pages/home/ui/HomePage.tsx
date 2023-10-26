import css from "./HomePage.module.css";

import { Header } from "widgets/header";
import { Content } from "widgets/content";

export const HomePage = () => {
    return (
        <div className={css.homePage}>
            <Header />
            <Content />
        </div>
    );
};
