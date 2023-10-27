import css from "./Image.module.css";

import cn from "classnames";

interface ImageProps {
    src?: string;
    variant: "small" | "medium" | "big";
}

export const Image: React.FC<ImageProps> = ({ src, variant }) => {
    const clazz = cn(css.image, css[`image_${variant}`]);

    return <img className={clazz} src={src} alt="image" />;
};
