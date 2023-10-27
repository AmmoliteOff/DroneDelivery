import css from "./ProfilePage.module.css";

import { useAppSelector } from "shared/model";
import { userSelector, ProfileCard } from "entities/user";
import { LogoutButton } from "features/logout";

export const ProfilePage = () => {
    const user = useAppSelector(userSelector);

    if (!user) return null;
    return (
        <div className={css.block}>
            <ProfileCard
                user={user}
                logout={<LogoutButton variant="blackWhite" />}
            />
        </div>
    );
};
