import css from "./LogoutButton.module.css";

import { Button } from "shared/ui";

export const LogoutButton = () => {
    return (
        <Button className={css.logoutButton} variant="text">
            выйти с аккаунта
        </Button>
    );
};
