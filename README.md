# DroneDelivery
## Backend:
Необходимо создать базу данных postgress.
После создания БД можно переходить к настройке приложения:
1. Перейти в src/main/resources
2. Открыть файл application.properties
3. Изменить данные для БД
   
### Сборка проекта
1. Скопировать проект с GitHub
2. Перейти в папку backend
3. Убедится, что в системе установлена `Java (Maven с поддержкой)` 17 версии и `JAVA_HOME`
4. Установить Maven в систему
5. `start mvn clean install`
6. Запустить сервер `java -jar JARPACKAGENAME`

## Запуск через Docker-compose:
1. Настроить application.properties `(Backend)`
2. Настроить constants.js `(Frontend)`
3. ***ВАЖНО*** изменить логин и пароль от базы данных в `docker-compose.yml`
4. Перейти в папку с проектом -> запустить ядро `Docker` -> прописать `docker-compose build` -> прописать `docker-compose up`
