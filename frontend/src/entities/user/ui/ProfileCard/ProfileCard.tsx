import css from "./ProfileCard.module.css";

import { type User } from "entities/user";
import { Image } from "shared/ui";

interface ProfileCardProps {
    user: User;

    logout?: React.ReactNode;
}

export const ProfileCard: React.FC<ProfileCardProps> = ({ user, logout }) => {
    return (
        <div className={css.card}>
            <div className={css.infoBlock}>
                <Image
                    src="https://klike.net/uploads/posts/2023-01/1674365337_3-31.jpg"
                    variant="medium"
                    className={css.avatar}
                />
                <div className={css.info}>
                    <p>
                        Идентификатор:{" "}
                        <span className={css.span}>{user.id}</span>
                    </p>
                    <p>
                        Имя: <span className={css.span}>{user.name}</span>
                    </p>
                    <p>
                        Фамилия:{" "}
                        <span className={css.span}>{user.surname}</span>
                    </p>
                </div>
            </div>
            <div className={css.buttonActions}>{logout && logout}</div>
        </div>
    );
};
