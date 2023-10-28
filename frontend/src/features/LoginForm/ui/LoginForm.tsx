import css from "./LoginForm.module.css";

import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

import { Button } from "shared/ui";
import {
    type LoginFormSchema,
    loginFormSchema,
} from "../model/loginFormSchema";
import { useAppDispatch } from "shared/model";
import { loginAsync } from "entities/user";

interface LoginFormProps {
    onComplete?: () => void;
}

export const LoginForm: React.FC<LoginFormProps> = ({ onComplete }) => {
    const dispatch = useAppDispatch();
    const {
        setError,
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<LoginFormSchema>({
        resolver: yupResolver(loginFormSchema),
    });

    const onSubmit = (data: LoginFormSchema) => {
        dispatch(loginAsync(data))
            .unwrap()
            .then(() => {
                // if (data.userRole === "DRONES_SENDER") {
                //     navigate("/home");
                // } else if (data.userRole === "DRONES_MASTER") {
                //     navigate("/drones");
                // }
                if (onComplete) onComplete();
            })
            .catch((error: { message: string }) => {
                setError("password", {
                    message: error.message,
                });
            });
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)} className={css.form}>
            <div className={css.formblock}>
                <label htmlFor="username">Username</label>
                <input
                    {...register("username")}
                    id="email"
                    type="text"
                    className={css.input}
                />
                <p className={css.error}>{errors.username?.message}</p>
            </div>
            <div className={css.formblock}>
                <label htmlFor="password">Password</label>
                <input
                    {...register("password")}
                    id="password"
                    type="password"
                    className={css.input}
                />
                <p className={css.error}>{errors.password?.message}</p>
            </div>
            <Button type="submit" className={css.button} variant="button">
                Войти
            </Button>
        </form>
    );
};
