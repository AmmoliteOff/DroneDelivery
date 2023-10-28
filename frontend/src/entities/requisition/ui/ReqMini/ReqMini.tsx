import css from "./ReqMini.module.css";

import cn from "classnames";

import { type Requisition } from "entities/requisition";

interface ReqMiniProps {
    req: Requisition;
    onClick?: () => void;
    active?: boolean;
}

export const ReqMini: React.FC<ReqMiniProps> = ({ req, onClick, active }) => {
    const modifier = css[`order_active`];
    const clazz = cn(css.order, {
        [modifier]: active,
    });

    return (
        <div onClick={onClick} className={clazz}>
            Заказ <span>#{req.id}</span>
        </div>
    );
};
