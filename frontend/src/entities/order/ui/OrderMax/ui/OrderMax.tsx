import { Button, Image } from "shared/ui";
import css from "./OrderMax.module.css";

import { type Order } from "entities/order";

interface OrderMaxProps {
    order: Order;
    renderDrone: () => React.ReactNode;
}

export const OrderMax: React.FC<OrderMaxProps> = ({ order, renderDrone }) => {
    return (
        <div className={css.orderMax}>
            <div className={css.orderInfo}>
                {renderDrone()}

                <div className={css.droneButtons}>
                    <Button>Отправить дрона</Button>
                    <Button className={css.removeButton}>
                        Отменить заказ целиком
                    </Button>
                </div>
            </div>

            <div className={css.productInfo}>
                <div className={css.customer}>
                    <div className={css.customerInfo}>
                        <h3 className={css.name}>{order.customer.name}</h3>
                        <p>Адрес доставки: {order.deliveryAdress}</p>
                        <p>Номер телефона: {order.customer.telephoneNumber}</p>
                    </div>
                    <Button className={css.removeButton}>
                        Отменить эту часть заказа
                    </Button>
                </div>

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
