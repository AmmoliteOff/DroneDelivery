import css from "./Button.module.css";

import { ComponentProps } from "react";
import cn from "classnames";

interface ButtonProps extends ComponentProps<"button"> {
    variant?: "text" | "button";
}

export const Button: React.FC<ButtonProps> = ({
    variant = "button",
    children,
    className,
    disabled,
    ...props
}) => {
    const clazz = cn(
        css.button,
        css[`button_${variant}`],
        className,
        disabled && css.disabled
    );
    return (
        <button {...props} className={clazz} disabled={disabled}>
            {children}
        </button>
    );
};
