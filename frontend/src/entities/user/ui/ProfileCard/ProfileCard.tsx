import css from "./ProfileCard.module.css";

import { type User } from "entities/user";

interface ProfileCardProps {
    user: User;
}

export const ProfileCard: React.FC<ProfileCardProps> = ({ user }) => {
    return <div className={css.card}></div>;
};
