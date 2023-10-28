import css from "./Content.module.css";

import { useState } from "react";
import { useAppSelector } from "shared/model";

import {
    type Requisition,
    ReqMini,
    ReqMax,
    reqsSelector,
} from "entities/requisition";
import { Drone } from "entities/drone";

export const Content = () => {
    const reqs = useAppSelector(reqsSelector);

    const [selectedReq, setSelectedReq] = useState<Requisition | undefined>();

    const onClickReq = (req: Requisition) => {
        setSelectedReq(req);
    };

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
