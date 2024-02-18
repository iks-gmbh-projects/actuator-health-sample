# Spring Boot Actuator Health Sample App

## Description
Beispiel Spring Boot Anwendung zur Demonstration Spring Actuator Health Endpoint und
Verarbeitungsstatistik Plugin.
Die Werte können zum Monitoring der Anwendung verwendet werden.

## Home
https://github.com/hjaegleiks/actuator-health-sample

## How to Install and Run the Project

### Prerequisites
* [Java SDK](https://openjdk.org/) 1.8+
* [Apache Maven](https://maven.apache.org/) 3+
* [Spring Boot](https://spring.io/projects/spring-boot) 2.7+
* Java Modul actuator-health-processstatistic.jar, GitHub Repository https://github.com/hjaegleiks/actuator-health-processstatistic

### Install
Projekt aus GitHub Repository klonen:<br/>
https://github.com/hjaegleiks/actuator-health-sample.git

Java Archiv Datei (JAR) erstellen mit Build Tool Maven und pom.xml:
```shell
mvn clean install
```
Erzeugt im Projekt-Unterverzeichnis target Datei actuator-health-sample-app.jar.

### Usage
Spring Boot Anwendung starten, über Befehlszeile oder Java IDE.
```shell
java -jar target\actuator-health-sample-app.jar
```
Wenn Spring Boot Anwendung gestartet ist (siehe Konsolenausgabe "Started ActuatorHealthSampleApp in xxx seconds"),
dann im Internetbrowser URL aufrufen:<br/>
http://localhost:8080/actuatorhealthsample/greeting?name=Mustermann

### Login
Nutzername: user<br/>
Passwort: password1<br/>
(siehe Konfigurationsdatei application.properties)

### Aufruf Beispielanwendung
http://localhost:8080/actuatorhealthsample/greeting?name=Mustermann

Um Fehlereinträge zu erzeugen, Parameter 'name' leer lassen.

### Aufruf Health Endpoint
http://localhost:8080/actuatorhealthsample/actuator/health

### Aufruf Health Endpoint Verarbeitungsstatistikwerte abrufen
http://localhost:8080/actuatorhealthsample/actuator/healthProcessStatistic/get/errorCounter

### Aufruf Health Endpoint Verarbeitungsstatistikwerte zurücksetzen
http://localhost:8080/actuatorhealthsample/actuator/healthProcessStatistic/reset?reset=true

.

## Author
H. Jägle<br/>
IKS GmbH<br/>
Hilden, Germany<br/>
http://www.iks-gmbh.com

## Contributing
Contributions, issues and feature requests are welcome.
Feel free to contact me.

## License
Copyright &copy; 2024 H. Jägle, IKS GmbH<br/>
This project is [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) licensed.
