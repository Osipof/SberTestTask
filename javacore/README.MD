Каркасное приложение JCDraft
==

## Сборка 

### Версия JAVA
Сборка и запуск проекта выполняется в среде JDK 17

### Сборка проекта и запуск тестов
mvn clean install

### Локальный запуск
mvn compile exec:java -Dexec.mainClass=sbp.sberuniversity.jcore.draft.host.Application

## Примеры запросов
Все примеры [запросов](requests/requests.http) выполняются с помощью встроенного в IDEA клиента HTTPClient