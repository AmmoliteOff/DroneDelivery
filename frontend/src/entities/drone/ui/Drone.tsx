import css from "./Drone.module.css";

import cn from "classnames";

import { type IDrone } from "../model/";
import { Image } from "shared/ui";

interface DroneProps {
    drone: IDrone;
    onClick?: () => void;
}

interface DroneCharhingIndicatorProps {
    charging: number;
}

const DroneCharhingIndicator: React.FC<DroneCharhingIndicatorProps> = ({
    charging,
}) => {
    const clazz = cn(css.charging, {
        [css[`charging_low`]]: charging <= 40,
        [css[`charging_medium`]]: charging > 40 && charging < 70,
        [css[`charging_big`]]: charging >= 70,
    });

    return <div className={clazz}></div>;
};

export const Drone: React.FC<DroneProps> = ({ drone, onClick }) => {
    const clazz = cn(css.drone, {
        [css["drone_clickable"]]: !!onClick,
    });

    return (
        <div onClick={onClick} className={clazz}>
            <Image src={drone.img} variant="big" className={css.droneImg} />
            <div className={css.droneInfo}>
                <p className={css.title}>Дрон #{drone.id}</p>
                <div className={css.droneInfo}>
                    <div className={css.droneChargingBlock}>
                        <DroneCharhingIndicator charging={drone.charging} />
                        <p>{drone.charging}%</p>
                    </div>
                    <div className={css.droneWeightBlock}>
                        <Image
                            variant="small"
                            src="https://res.cloudinary.com/do1tmxguz/image/upload/v1698408831/muiofvmjclik3xwe2mu2.png"
                        />
                        {drone.maxWeight} кг
                    </div>
                </div>
            </div>
        </div>
    );
};
