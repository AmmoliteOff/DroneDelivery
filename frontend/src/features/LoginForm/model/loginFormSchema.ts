import * as yup from "yup";

export interface LoginFormSchema {
    email: string;
    password: string;
}

export const loginFormSchema = yup
    .object({
        email: yup
            .string()
            .min(6, "Минимально 5 символов")
            .max(24, "Максимально 24 символа")
            .email("Введите корректный адрес электронной почты")
            .required("Поле email обязательное"),
        password: yup
            .string()
            .min(6, "Минимально 5 символов")
            .max(24, "Максимально 24 символа")
            .required(),
    })
    .required();
