import css from "./LogoutButton.module.css";

import { Button } from "shared/ui";

interface LogoutButtonProps {
    variant?: "text" | "blackWhite";
}

export const LogoutButton: React.FC<LogoutButtonProps> = ({
    variant = "text",
}) => {
    return (
        <Button variant={variant} className={css.logoutButton}>
            выйти с аккаунта
        </Button>
    );
};
