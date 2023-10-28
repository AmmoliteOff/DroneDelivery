import css from "./Content.module.css";

import { useState } from "react";

import { type Requisition, ReqMini, ReqMax } from "entities/requisition";
import { Drone } from "entities/drone";

const REQS: Requisition[] = [
    {
        id: "864",
        status: "CREATED",
        orders: [
            {
                deliveryAdress: "Кольцовская д. 1",
                latitude: 55.72,
                longitude: 37.44,
                id: "951",
                customer: {
                    name: "Ваня",
                    surname: "Иванов",
                    telephoneNumber: "8594595943",
                },
                products: [
                    { id: "1", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "2", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
            {
                deliveryAdress: "Кольцовская д. 1",
                latitude: 55.8,
                longitude: 37.4,
                id: "32",
                customer: {
                    name: "Ваня",
                    surname: "Иванов",
                    telephoneNumber: "8594595943",
                },
                products: [
                    { id: "3", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "4", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
        ],
        drone: {
            id: "12",
            img: "drone.png",
            maxWeight: 10,
            charging: 90,
            latitude: 55.76,
            longitude: 37.64,
        },
    },
    {
        id: "432",
        status: "CREATED",
        orders: [
            {
                id: "35",
                latitude: 55.72,
                longitude: 37.44,
                deliveryAdress: "Университетская д. 5",
                customer: {
                    name: "Сергей",
                    surname: "Сергеев",
                    telephoneNumber: "88005553535",
                },
                products: [
                    { id: "5", img: "banana.png", name: "Бананы", weight: 2 },
                    { id: "6", img: "banana.png", name: "Бананы", weight: 2 },
                ],
            },
        ],
        drone: {
            id: "321",
            img: "drone.png",
            maxWeight: 4,
            charging: 70,
            latitude: 55.76,
            longitude: 37.64,
        },
    },
];
export const Content = () => {
    const [selectedReq, setSelectedReq] = useState<Requisition | undefined>();

    const onClickReq = (req: Requisition) => {
        setSelectedReq(req);
    };

    return (
        <div className={css.content}>
            <div className={css.ordersMini}>
                <h2 className={css.ordersTitle}>Заказы:</h2>
                {REQS.map((req) => {
                    return (
                        <ReqMini
                            active={req.id === selectedReq?.id}
                            key={req.id}
                            onClick={() => onClickReq(req)}
                            req={req}
                        />
                    );
                })}
            </div>
            <div className={css.ordersMax}>
                {selectedReq ? (
                    <ReqMax
                        req={selectedReq}
                        renderDrone={(weight: number) => (
                            <Drone drone={selectedReq.drone} weight={weight} />
                        )}
                    />
                ) : (
                    <h1 className={css.selectOrderTitlte}>Выберите заказ</h1>
                )}
            </div>
        </div>
    );
};
