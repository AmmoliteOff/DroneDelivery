import css from "./Header.module.css";

import { Profile } from "entities/user";

export const Header = () => {
    return (
        <div className={css.header}>
            <h1 className={css.logo}>DroneDelivery</h1>

            <div className={css.actions}>
                <Profile
                    name={"Вася"}
                    surname={"Работник"}
                    img="https://klike.net/uploads/posts/2023-01/1674365337_3-31.jpg"
                />
            </div>
        </div>
    );
};
