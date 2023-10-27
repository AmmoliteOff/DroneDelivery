import { Button, Image } from "shared/ui";
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
                    <Image src={order.drone.img} variant="big" />
                    <div className={css.droneInfo}>
                        <p>Дрон #{order.drone.id}</p>
                        <div className={css.droneInfoBlock}>
                            <Image variant="small" src="weight.png" />
                        </div>
                    </div>
                </div>
                <div className={css.customer}>
                    <h3 className={css.name}>{order.customer.name}</h3>
                    <p>Адрес доставки: {order.deliveryAdress}</p>
                    <p>Номер телефона: {order.customer.telephoneNumber}</p>
                </div>
            </div>
            <div className={css.productInfo}>
                {order.products.map((product) => {
                    return (
                        <div key={product.id} className={css.productBlock}>
                            <div className={css.product}>
                                <Image src={product.img} variant="medium" />
                                <div className={css.productDescr}>
                                    <p>{product.name}</p>
                                    <div className={css.weight}>
                                        <Image
                                            variant="small"
                                            src="weight.png"
                                        />
                                        <p>{product.weight} кг</p>
                                    </div>
                                </div>
                            </div>
                            <Button className={css.button}>Добавить</Button>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};
