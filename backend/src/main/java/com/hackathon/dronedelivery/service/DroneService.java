package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Drone;
import com.hackathon.dronedelivery.model.GeoCoords;
import com.hackathon.dronedelivery.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    public void save(Drone drone){
        droneRepository.save(drone);
    }

    public Optional<Drone> findById(Long id) {
        return droneRepository.findById(id);
    }

    public boolean sendDrone(Drone drone){
        sendRequest("sendDrone", null);
        return true; //edit
    }

    public boolean setRoute(Map<GeoCoords, Long> route){
        sendRequest("setRoute", route);
        return true; //edit
    }

    private void sendRequest(String endpoint, Object object){
        try {
// Создание объекта URL
            URL url = new URL("http://127.0.0.1:13000/drone/"+endpoint);

// Открытие соединения
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

// Установка метода запроса
            con.setRequestMethod("POST");

// Установка заголовков
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

// Включение возможности отправки данных
            con.setDoOutput(true);

// Создание объекта для отправки данных
            DataOutputStream out = new DataOutputStream(con.getOutputStream());

// Создание объекта для преобразования в JSON
            String jsonInputString  = "";
            if(object!=null) {
                JSONObject jsonObject = new JSONObject(object);
                jsonInputString = jsonObject.toString();
            }
// Отправка данных
            out.writeBytes(jsonInputString);
            out.flush();
            out.close();

// Получение ответа
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

// Закрытие соединения
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
