import * as yup from "yup";

export interface LoginFormSchema {
    username: string;
    password: string;
}

export const loginFormSchema = yup
    .object({
        username: yup
            .string()
            .min(3, "Минимально 5 символов")
            .max(24, "Максимально 24 символа")
            .required("Поле обязательно"),
        password: yup
            .string()
            .min(3, "Минимально 5 символов")
            .max(24, "Максимально 24 символа")
            .required("Поле обязательно"),
    })
    .required();
