import css from "./LoginPage.module.css";

import { useNavigate } from "react-router-dom";
import { LoginForm } from "features/LoginForm";

export const LoginPage = () => {
    const navigate = useNavigate();

    const onComplete = () => {
        navigate("/home");
    };
    return (
        <div className={css.block}>
            <div className={css.content}>
                <div className={css.contentHeader}>
                    <h1>Логин</h1>
                </div>
                <div className={css.contentForm}>
                    <LoginForm onComplete={onComplete} />
                </div>
            </div>
        </div>
    );
};
