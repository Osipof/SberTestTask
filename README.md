# SberTestTask
### Выполнение тестового задания для вакансии Junior Java Developer.  
Проект представляет собой пример онлайн магазина мобильных телефонов, разработанного на Java с использованием Spring Boot. Включает в себя бэкенд-часть для управления данными о мобильных телефонах
Содержит в себе модель мобильного телефона `MobilePhone` и CRUD (создание, чтение, обновление и удаление) операции над ней.

## Стэк приложения
- Java 17
- Maven
- Spring Boot
- Spring Data JPA
- RESTful API
- Документирование кода JavaDoc
- Логгирование Slf4j
- Тестирование JUnit 

## Для запуска проекта:
1. Клонируйте репозиторий на свой компьютер
2. Подсоедините базу данных с таблицей "mobile_phone"
3. в application.properties поместить название базы данных, имя пользователя и пароль 
4. Скачать все зависимости для проекта с помощью maven `mvn clean install`
5. Запустить проект - `mvn spring-boot:run`
6. Проект будет запущен на порту по умолчанию (8080)

## Использование API:
- `/phones [GET]` - Получить список всех мобильных телефонов
- `/phones [POST]` - Сохранение нового мобильного телефона
- `/phones/{id} [GET]` - Получить информацию о мобильном телефоне по ID
- `/phones/{id} [PUT]` - Обновить информацию о мобильном телефоне по ID
- `/phones/{id} [DELETE]` - Удаление информации о мобильном телефоне по ID

### Таблица "mobile_phone" содержит следующие поля:
`id integer generated by default as identity primary key,`  
`brand  varchar(50) not null,`  
`model  varchar(50) not null,`  
`price  integer not null,`  
`color  varchar(20),`  
`amount integer not null`