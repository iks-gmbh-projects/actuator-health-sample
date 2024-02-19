# Spring Boot Actuator Health Sample App

## Description
Beispiel Spring Boot Anwendung zur Demonstration Spring Actuator Health Endpoint und Verarbeitungsstatistik Plugin.
Die Werte können zum Monitoring der Anwendung verwendet werden.

## Home
https://github.com/hjaegleiks/actuator-health-sample

## How to Install and Run the Project

### Prerequisites
* [Java SDK](https://openjdk.org/) 1.8+
* [Apache Maven](https://maven.apache.org/) 3+
* [Spring Boot](https://spring.io/projects/spring-boot) 2.7+
* Java Modul actuator-health-processstatistic.jar,
  GitHub Repository https://github.com/hjaegleiks/actuator-health-processstatistic

### Install
Projekt aus GitHub Repository in ein neues Projekt-Verzeichnis klonen:  
https://github.com/hjaegleiks/actuator-health-sample.git

Dort Java Archiv Datei (JAR) erstellen mit Build Tool Maven und `pom.xml`:
```shell
mvn clean install
```
Erzeugt im Projekt-Unterverzeichnis `target` Datei `actuator-health-sample-app.jar`.

### Usage
Spring Boot Anwendung starten, über Befehlszeile oder Java IDE.
```shell
java -jar target\actuator-health-sample-app.jar
```

### Aufruf Beispielanwendung
http://localhost:8080/actuatorhealthsample/greeting?name=Mustermann

Wenn Spring Boot Anwendung gestartet ist (siehe Konsolenausgabe "Started ActuatorHealthSampleApp in xxx seconds"),
dann im Internetbrowser URL aufrufen (HTTP GET):  
http://localhost:8080/actuatorhealthsample/greeting?name=Mustermann

### Login
Nutzername: user  
Passwort: password1  
(siehe Konfigurationsdatei `application.properties`)

Um mal Fehlereinträge zu provozieren, Parameter `name` leer lassen.

### Aufruf Health Endpoint
http://localhost:8080/actuatorhealthsample/actuator/health

### Aufruf Health Endpoint Verarbeitungsstatistik abrufen
http://localhost:8080/actuatorhealthsample/actuator/healthProcessStatistic/get/errorCounter

### Aufruf Health Endpoint Verarbeitungsstatistik zurücksetzen
http://localhost:8080/actuatorhealthsample/actuator/healthProcessStatistic/reset?reset=true


## Author
H. Jägle  
IKS GmbH  
Hilden, Germany  
http://www.iks-gmbh.com

## Contributing
Contributions, issues and feature requests are welcome.
Feel free to contact me.

## License
Copyright &copy; 2024 H. Jägle, IKS GmbH  
This project is [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) licensed.
