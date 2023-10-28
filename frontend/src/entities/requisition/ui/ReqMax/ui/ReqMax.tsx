import css from "./ReqMax.module.css";

import { useState, useEffect } from "react";

import { Button, Image, YandexMap } from "shared/ui";

import { type Requisition, type Product } from "entities/requisition";

interface OrderMaxProps {
    req: Requisition;
    renderDrone: (weight: number) => React.ReactNode;
}

interface Added {
    product: Product;
    latitude: number;
    longitude: number;
}

export const ReqMax: React.FC<OrderMaxProps> = ({ req, renderDrone }) => {
    const [addedProducts, setAddedProducts] = useState<Added[]>([]);

    const productsWeight = addedProducts.reduce(
        (total, added) => total + added.product.weight,
        0
    );

    const onClickAddProduct = (added: Added) => {
        setAddedProducts((old) => [...old, added]);
    };

    useEffect(() => {
        return () => {
            setAddedProducts([]);
        };
    }, [req]);

    return (
        <div className={css.orderMax}>
            <div className={css.orderInfo}>
                <YandexMap
                    defaultGeometry={[
                        req.drone.currentLatitude,
                        req.drone.currentLongitude,
                    ]}
                    allGeometry={addedProducts.map((added) => [
                        added.latitude,
                        added.longitude,
                    ])}
                />
                <div className={css.orderInfoBlock}>
                    {renderDrone(productsWeight)}
                    <div className={css.droneButtons}>
                        <Button className={css.sendDroneButton}>
                            Отправить дрона
                        </Button>
                        {/* <Button className={css.removeButton}>
                        Отменить заказ целиком
                    </Button> */}
                    </div>
                </div>
            </div>

            {req.drone.orders.map((order) => {
                return (
                    <div key={order.id} className={css.productInfo}>
                        <div className={css.customer}>
                            <div className={css.customerInfo}>
                                <h3 className={css.name}>
                                    {order.customerName}
                                </h3>
                                <p>Адрес доставки: {order.customerAddress}</p>
                                <p>Номер телефона: {order.customerNumber}</p>
                            </div>
                            {/* <Button className={css.removeButton}>
                                Отменить эту часть заказа
                            </Button> */}
                        </div>

                        {order.products.map((product) => {
                            return (
                                <div
                                    key={product.id}
                                    className={css.productBlock}
                                >
                                    <div className={css.product}>
                                        <Image
                                            variant="medium"
                                            src={product.img}
                                        />
                                        <div className={css.productDescr}>
                                            <p className={css.productName}>
                                                {product.name}
                                            </p>
                                            <div className={css.weight}>
                                                <Image
                                                    variant="small"
                                                    src="weight.png"
                                                />
                                                <p>{product.weight} кг</p>
                                            </div>
                                        </div>
                                    </div>
                                    <Button
                                        disabled={
                                            !!addedProducts.find(
                                                (item) =>
                                                    item.product.id ===
                                                    product.id
                                            ) ||
                                            productsWeight + product.weight >
                                                req.drone.maxWeight
                                        }
                                        onClick={() =>
                                            onClickAddProduct({
                                                product: product,
                                                latitude: order.latitude,
                                                longitude: order.longitude,
                                            })
                                        }
                                        className={css.button}
                                    >
                                        Добавить
                                    </Button>
                                </div>
                            );
                        })}
                    </div>
                );
            })}
        </div>
    );
};
