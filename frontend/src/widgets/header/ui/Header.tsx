import css from "./Header.module.css";

import { useNavigate } from "react-router-dom";

import { Profile } from "entities/user";
import { LogoutButton } from "features/logout";

export const Header = () => {
    const navigate = useNavigate();

    const onClickLogo = () => {
        navigate("/home");
    };
    return (
        <div className={css.header}>
            <h1 onClick={onClickLogo} className={css.logo}>
                DroneDelivery
            </h1>

            <div className={css.actions}>
                <Profile
                    logout={<LogoutButton />}
                    name={"Вася"}
                    surname={"Работник"}
                    img="https://klike.net/uploads/posts/2023-01/1674365337_3-31.jpg"
                />
            </div>
        </div>
    );
};
