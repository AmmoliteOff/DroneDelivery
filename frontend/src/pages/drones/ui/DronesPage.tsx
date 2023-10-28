import css from "./DronePage.module.css";

import { useState } from "react";

import { useNavigate } from "react-router-dom";

import { useAppSelector, useAppDispatch } from "shared/model";
import { api } from "shared/api";
import {
    dronesLoadingSelector,
    dronesSelector,
    getDronesAsync,
} from "entities/drone";

import { Drone } from "entities/drone";
import { config } from "shared/api";
import { useEffect } from "react";
import { Button } from "shared/ui";

export const DronesPage = () => {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const drones = useAppSelector(dronesSelector);

    const [formData, setFormData] = useState({
        charge: "",
        fullChargeDistance: "",
        maxWeight: "",
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    // console.log({
    //     droneCharge: "",
    //     maxDistance: "",
    //     maxWeight: "",
    // });

    const isLoading = useAppSelector(dronesLoadingSelector);

    const onClickDrone = (droneId: number) => {
        navigate(`/drones/${droneId}`);
    };

    useEffect(() => {
        dispatch(getDronesAsync());
    }, []);

    const onSubmit = (e) => {
        e.preventDefault();
        console.log("Отправляем данные:", formData);
        api.post(config.paths.drones.add, formData);
    };

    if (isLoading) return <h1>Загрузка дронов...</h1>;

    return (
        <div className={css.drones}>
            <div className={css.formBlock}>
                <h2 className={css.addTitle}>Добавить дрона</h2>
                <form onSubmit={onSubmit} className={css.form}>
                    <input
                        value={formData.charge}
                        name="charge"
                        onChange={handleInputChange}
                        type="text"
                        placeholder="Заряд дрона"
                    />
                    <input
                        name="fullChargeDistance"
                        type="text"
                        placeholder="Макс. расстояние"
                        value={formData.fullChargeDistance}
                        onChange={handleInputChange}
                    />
                    <input
                        name="maxWeight"
                        type="text"
                        placeholder="Макс. носимый вес"
                        value={formData.maxWeight}
                        onChange={handleInputChange}
                    />
                    <Button type="submit">Добавить</Button>
                </form>
            </div>
            <div className={css.dronesList}>
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
        </div>
    );
};
