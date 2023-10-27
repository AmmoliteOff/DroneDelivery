import css from "./HomePage.module.css";

import { Content } from "widgets/content";

export const HomePage = () => {
    return (
        <div className={css.homePage}>
            <Content />
        </div>
    );
};
