import css from "./LogoutButton.module.css";

import { useNavigate } from "react-router-dom";

import { Button } from "shared/ui";
import { useAppDispatch } from "shared/model";
import { clearUserData } from "entities/user";

interface LogoutButtonProps {
    variant?: "text" | "button";
}

export const LogoutButton: React.FC<LogoutButtonProps> = ({
    variant = "text",
}) => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    const onClickLogout = () => {
        dispatch(clearUserData());
        navigate("/login");
    };

    return (
        <Button
            onClick={onClickLogout}
            variant={variant}
            className={css.logoutButton}
        >
            выйти с аккаунта
        </Button>
    );
};
