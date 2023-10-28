import { YMaps, Map, Polyline, Placemark } from "@pbe/react-yandex-maps";

interface YandexMapProps {
    defaultGeometry: number[];
    allGeometry: number[][];
}

export const YandexMap: React.FC<YandexMapProps> = ({
    defaultGeometry,
    allGeometry,
}) => {
    return (
        <YMaps>
            <Map
                width="100%"
                height="400px"
                defaultState={{
                    center: defaultGeometry,
                    zoom: 10,
                }}
            >
                <Placemark
                    geometry={defaultGeometry}
                    options={{
                        iconLayout: "default#image",
                        iconImageHref:
                            "https://cdn2.iconfinder.com/data/icons/innovation-technology-1/512/tech_0011-1024.png",
                        iconImageSize: [64, 64], // Размер вашей иконки
                        iconImageOffset: [-16, -32], // Смещение иконки
                    }}
                />

                {allGeometry.map((geometry, i) => {
                    return (
                        <div key={i}>
                            <Placemark geometry={geometry} />
                        </div>
                    );
                })}

                <Polyline
                    geometry={[defaultGeometry, ...allGeometry]}
                    options={{
                        strokeColor: "#000",
                        strokeWidth: 4,
                        strokeOpacity: 0.5,
                    }}
                />
            </Map>
        </YMaps>
    );
};
