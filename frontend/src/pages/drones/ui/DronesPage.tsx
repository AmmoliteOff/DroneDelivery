import css from "./DronePage.module.css";

import { useNavigate } from "react-router-dom";

import { useAppSelector } from "shared/model";
import { dronesSelector } from "entities/drone";

import { Drone } from "entities/drone";

export const DronesPage = () => {
    const navigate = useNavigate();
    const drones = useAppSelector(dronesSelector);

    const onClickDrone = (droneId: string) => {
        navigate(`/drones/${droneId}`);
    };

    return (
        <div className={css.drones}>
            {drones.map((drone) => {
                return (
                    <Drone
                        onClick={() => onClickDrone(drone.id)}
                        key={drone.id}
                        drone={drone}
                    />
                );
            })}
        </div>
    );
};
