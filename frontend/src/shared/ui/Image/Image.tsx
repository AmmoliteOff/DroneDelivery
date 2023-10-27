import css from "./Image.module.css";

import cn from "classnames";

interface ImageProps {
    src?: string;
    variant: "small" | "medium" | "big";
    className?: string;
    onClick?: () => void;
}

export const Image: React.FC<ImageProps> = ({
    src,
    variant,
    className,
    onClick,
}) => {
    const clazz = cn(css.image, css[`image_${variant}`], className);

    if (!src) return <div className={css.dummy}></div>;

    return <img onClick={onClick} className={clazz} src={src} alt="image" />;
};
