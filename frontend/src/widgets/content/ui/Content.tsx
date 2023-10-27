import css from "./Content.module.css";

import { useState } from "react";

import { type Order, OrderMini, OrderMax } from "entities/order";

const ORDERS: Order[] = [
    {
        id: "864",
        deliveryAdress: "Кольцовская д. 1",
        customer: {
            name: "Ваня",
            surname: "Иванов",
            telephoneNumber: "8594595943",
        },
        products: [
            { id: "1", img: "banana.png", name: "Бананы", weight: 2 },
            { id: "2", img: "banana.png", name: "Бананы", weight: 2 },
        ],
        drone: {
            id: "12",
            img: "drone.png",
        },
    },
    {
        id: "432",
        deliveryAdress: "Университетская д. 5",
        customer: {
            name: "Сергей",
            surname: "Сергеев",
            telephoneNumber: "88005553535",
        },
        products: [{ id: "4", img: "pear.png", name: "Груши ", weight: 1 }],
        drone: {
            id: "321",
            img: "drone.png",
        },
    },
];

export const Content = () => {
    const [selectedOrder, setSelectedOrder] = useState<Order | undefined>();

    const onClickOrder = (order: Order) => {
        setSelectedOrder(order);
    };

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
                {selectedOrder ? (
                    <OrderMax order={selectedOrder} />
                ) : (
                    "Выберите заказ"
                )}
            </div>
        </div>
    );
};
