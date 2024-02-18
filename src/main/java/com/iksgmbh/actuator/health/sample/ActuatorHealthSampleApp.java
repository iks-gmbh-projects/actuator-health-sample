package com.iksgmbh.actuator.health.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.iksgmbh.actuator.health.sample",
        "com.iksgmbh.actuator.health.procstat"
})
public class ActuatorHealthSampleApp {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorHealthSampleApp.class, args);
    }
}
