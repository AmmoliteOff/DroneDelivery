import css from "./Content.module.css";

import { useState } from "react";

import { type Order, OrderMini, OrderMax } from "entities/order";
import { Drone } from "entities/drone";

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
            maxWeight: 10,
            charging: 90,
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
            maxWeight: 4,
            charging: 70,
        },
    },
];

export const Content = () => {
    const [orders, setOrders] = useState<Order[]>(ORDERS);
    const [selectedOrder, setSelectedOrder] = useState<Order | undefined>();

    const onClickOrder = (order: Order) => {
        setSelectedOrder(order);
    };

    return (
        <div className={css.content}>
            <div className={css.ordersMini}>
                <h2 className={css.ordersTitle}>Заказы:</h2>
                {orders.map((order) => {
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
                    <OrderMax
                        order={selectedOrder}
                        renderDrone={() => (
                            <Drone drone={selectedOrder.drone} />
                        )}
                    />
                ) : (
                    <h1 className={css.selectOrderTitlte}>Выберите заказ</h1>
                )}
            </div>
        </div>
    );
};
