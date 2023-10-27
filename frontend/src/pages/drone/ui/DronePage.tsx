import css from "./DronePage.module.css";

import { useParams } from "react-router-dom";

import { useAppSelector } from "shared/model";
import { droneByIdSelector } from "entities/drone";

import { Drone } from "entities/drone";
import { Button } from "shared/ui";

export const DronePage = () => {
    const { droneId } = useParams();

    const drone = useAppSelector((state) => droneByIdSelector(state, droneId));

    if (!drone) return <h1>Дрон не найден</h1>;

    return (
        <div className={css.block}>
            <div className={css.page}>
                <Drone drone={drone} />
                <div className={css.buttons}>
                    <Button className={css.button}>Удалить дрон</Button>
                </div>
            </div>
        </div>
    );
};
