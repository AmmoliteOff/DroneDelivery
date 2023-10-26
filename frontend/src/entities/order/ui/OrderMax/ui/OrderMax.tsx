import { Button } from "shared/ui";
import css from "./OrderMax.module.css";

import { type Order } from "entities/order";

interface OrderMaxProps {
    order: Order;
}

export const OrderMax: React.FC<OrderMaxProps> = ({ order }) => {
    return (
        <div className={css.orderMax}>
            <div className={css.orderInfo}>
                <div className={css.drone}>
                    <img className={css.droneImg} src="drone.png" alt="drone" />
                    <div className={css.droneInfo}>
                        <p>Дрон #{order.droneId}</p>
                    </div>
                </div>
                <div className={css.customer}>
                    <h3 className={css.name}>Егор</h3>
                    <p>Адрес доставки: Университетская площадь</p>
                    <p>Номер телефона: 88005553535</p>
                </div>
            </div>
            <div className={css.productInfo}>
                <div className={css.product}>
                    <img src="banana.png" className={css.banana} alt="banana" />
                    <div className={css.productDescr}>
                        <p>Бананы</p>
                        <div className={css.weight}>
                            <img
                                className={css.weightIcon}
                                src="weight.png"
                                alt="weight"
                            />
                            <p>2 кг</p>
                        </div>
                    </div>
                </div>

                <Button>Добавить</Button>
            </div>
        </div>
    );
};
