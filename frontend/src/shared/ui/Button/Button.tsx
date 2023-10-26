import css from "./Button.module.css";

import { ComponentProps } from "react";
import cn from "classnames";

interface ButtonProps extends ComponentProps<"button"> {
    variant?: "text" | "blackWhite";
}

export const Button: React.FC<ButtonProps> = ({
    variant = "blackWhite",
    children,
    className,
    ...props
}) => {
    const clazz = cn(css.button, css[`button_${variant}`], className);
    return (
        <button {...props} className={clazz}>
            {children}
        </button>
    );
};
