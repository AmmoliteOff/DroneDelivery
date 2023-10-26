import css from "./LoginPage.module.css";

import { LoginForm } from "features/LoginForm";

export const LoginPage = () => {
    return (
        <div className={css.block}>
            <div className={css.content}>
                <div className={css.contentHeader}>
                    <h1>Логин</h1>
                </div>
                <div className={css.contentForm}>
                    <LoginForm />
                </div>
            </div>
        </div>
    );
};
