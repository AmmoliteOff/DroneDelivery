import css from "./Content.module.css";

import { useState } from "react";

import { type Order, OrderMini, OrderMax } from "entities/order";

const ORDERS: Order[] = [
    {
        id: "864",
        deliveryAdress: "Университетская д. 1",
        customerNumber: "8594595943",
        products: [],
        droneId: "213",
    },
    {
        id: "432",
        products: [],
        deliveryAdress: "Университетская д. 1",
        customerNumber: "88005553535",
        droneId: "321",
    },
];

export const Content = () => {
    const [selectedOrder, setSelectedOrder] = useState<Order | undefined>();

    const onClickOrder = (order: Order) => {
        setSelectedOrder(order);
    };

    console.log(selectedOrder);

    return (
        <div className={css.content}>
            <div className={css.ordersMini}>
                <h2 className={css.ordersTitle}>Заказы:</h2>
                {ORDERS.map((order) => {
                    return (
                        <OrderMini
                            active={order.id === selectedOrder?.id}
                            key={order.id}
                            onClick={() => onClickOrder(order)}
                            order={order}
                        />
                    );
                })}
            </div>
            <div className={css.ordersMax}>
                {selectedOrder && <OrderMax order={selectedOrder} />}
            </div>
        </div>
    );
};
