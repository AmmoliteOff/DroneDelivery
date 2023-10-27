import { useAppSelector } from "shared/model";

import { userSelector } from "entities/user";
import { ProfileCard } from "entities/user/ui/ProfileCard/ProfileCard";

export const ProfilePage = () => {
    const user = useAppSelector(userSelector);

    if (!user) return null;
    return (
        <div>
            <ProfileCard user={user} />
        </div>
    );
};
