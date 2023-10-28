import css from "./Content.module.css";

import { useState, useEffect } from "react";

import {
    type Requisition,
    ReqMini,
    ReqMax,
    reqsSelector,
    reqsLoadingSelector,
    getRequisitionsAsync,
} from "entities/requisition";
import { Drone } from "entities/drone";
import { useAppSelector, useAppDispatch } from "shared/model";

// const REQS: Requisition[] = [
//     {
//         id: 1,
//         drone: {
//             id: 1,
//             orders: [
//                 {
//                     id: 1,
//                     customerAddress: "45 стрелковой дивизии 259/7",
//                     customerName: "Егор",
//                     customerNumber: "89601082785",
//                     products: [
//                         {
//                             id: 1,
//                             weight: 3,
//                             name: "Яблоко",
//                             img: "https://res.cloudinary.com/do1tmxguz/image/upload/v1698485259/onpeb7suvzrpbrzh9edx.png",
//                         },
//                     ],
//                     longitude: 39.20667,
//                     latitude: 51.66646,
//                 },
//             ],
//             charge: 100,
//             maxWeight: 9,
//             currentLongitude: 39.20567,
//             currentLatitude: 51.65646,
//             imageLink:
//                 "https://yns1.ru/attachments/Image/dji-mavic-air.png?template=generic",
//         },
//         status: "CREATED",
//     },
//     {
//         id: 2,
//         drone: {
//             id: 2,
//             orders: [
//                 {
//                     id: 1,
//                     customerAddress: "45 стрелковой дивизии 259/7",
//                     customerName: "Егор",
//                     customerNumber: "89601082785",
//                     products: [
//                         {
//                             id: 1,
//                             weight: 3,
//                             name: "Яблоко",
//                             img: "https://res.cloudinary.com/do1tmxguz/image/upload/v1698485259/onpeb7suvzrpbrzh9edx.png",
//                         },
//                     ],
//                     longitude: 55.72,
//                     latitude: 37.44,
//                 },
//             ],
//             charge: 100,
//             maxWeight: 5,
//             currentLongitude: 39.20567,
//             currentLatitude: 51.65646,
//             imageLink:
//                 "https://yns1.ru/attachments/Image/dji-mavic-air.png?template=generic",
//         },
//         status: "CREATED",
//     },
// ];
export const Content = () => {
    const dispatch = useAppDispatch();

    const reqs = useAppSelector(reqsSelector);

    const isLoadingReq = useAppSelector(reqsLoadingSelector);

    const [selectedReq, setSelectedReq] = useState<Requisition | undefined>();

    const onClickReq = (req: Requisition) => {
        setSelectedReq(req);
    };

    useEffect(() => {
        dispatch(getRequisitionsAsync());
    }, []);

    if (isLoadingReq || !reqs) return <h1>Загрузка...</h1>;

    return (
        <div className={css.content}>
            <div className={css.ordersMini}>
                <h2 className={css.ordersTitle}>Заказы:</h2>
                {reqs.map((req) => {
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
