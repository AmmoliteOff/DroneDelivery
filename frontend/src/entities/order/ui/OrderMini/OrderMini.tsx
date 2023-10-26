import css from "./OrderMini.module.css";

import cn from "classnames";

import { type Order } from "entities/order/model";

interface OrderMiniProps {
    order: Order;
    onClick?: () => void;
    active?: boolean;
}

export const OrderMini: React.FC<OrderMiniProps> = ({
    order,
    onClick,
    active,
}) => {
    const modifier = css[`order_active`];
    const clazz = cn(css.order, {
        [modifier]: active,
    });

    return (
        <div onClick={onClick} className={clazz}>
            <div className={css.id}>
                Заказ <span>#{order.id}</span>
            </div>
        </div>
    );
};
