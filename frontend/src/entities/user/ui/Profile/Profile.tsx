import css from "./Profile.module.css";

import { useNavigate } from "react-router-dom";

import { Image } from "shared/ui";

interface ProfileProps {
    name: string;
    surname: string;
    img: string;

    logout?: React.ReactNode;
}

export const Profile: React.FC<ProfileProps> = ({
    name,
    surname,
    img,
    logout,
}) => {
    const navigate = useNavigate();

    const onClickAvatar = () => {
        navigate("/profile");
    };

    return (
        <div className={css.profile}>
            <div className={css.infoBlock}>
                <h2 className={css.info}>
                    {name} {surname}
                </h2>
                {logout && logout}
            </div>
            <Image
                onClick={onClickAvatar}
                src={img}
                variant="medium"
                className={css.avatar}
            />
        </div>
    );
};