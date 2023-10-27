import css from "./Header.module.css";

import { useNavigate } from "react-router-dom";

import { Profile, userSelector } from "entities/user";
import { LogoutButton } from "features/logout";
import { useAppSelector } from "shared/model";

export const Header = () => {
    const user = useAppSelector(userSelector);
    const navigate = useNavigate();

    const onClickLogo = () => {
        navigate("/home");
    };

    if (!user) return null;

    return (
        <div className={css.header}>
            <h1 onClick={onClickLogo} className={css.logo}>
                DroneDelivery
            </h1>

            <div className={css.actions}>
                <Profile
                    logout={<LogoutButton />}
                    name={user?.name}
                    surname={user?.surname}
                    img="https://klike.net/uploads/posts/2023-01/1674365337_3-31.jpg"
                />
            </div>
        </div>
    );
};
