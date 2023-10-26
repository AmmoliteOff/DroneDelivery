import css from "./Profile.module.css";

interface ProfileProps {
    name: string;
    surname: string;
    img: string;
}

export const Profile: React.FC<ProfileProps> = ({ name, surname, img }) => {
    return (
        <div className={css.profile}>
            <h2 className={css.info}>
                {name} {surname}
            </h2>
            <img className={css.avatar} src={img} alt="avatar" />
        </div>
    );
};
