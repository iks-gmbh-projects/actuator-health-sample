package com.iksgmbh.actuator.health.sample.controller;

import com.iksgmbh.actuator.health.procstat.model.HealthProcessStatisticData;
import com.iksgmbh.actuator.health.sample.health.SampleHealthProcessStatisticDataKey;
import com.iksgmbh.actuator.health.sample.model.Greeting;
import com.iksgmbh.actuator.health.sample.security.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private static final String template = "Hallo %s, wie geht's?";

    private final AtomicLong counter = new AtomicLong();

    private final AuthenticationFacade authenticationFacade;

    private final HealthProcessStatisticData healthProcessStatisticData;


    @Autowired
    public GreetingController(final AuthenticationFacade authenticationFacade,
            final HealthProcessStatisticData healthProcessStatisticData) {
        this.authenticationFacade = authenticationFacade;
        this.healthProcessStatisticData = healthProcessStatisticData;
    }


    @GetMapping("greeting")
    public ResponseEntity<?> greeting(@RequestParam(value = "name", defaultValue = "") String name) {

        log.info("Endpoint greeting called by: {}", authenticationFacade.getCurrentUserName());
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingRequest);

        Greeting greeting = new Greeting(counter.incrementAndGet(), name);

        if (greeting.isValid()) {
            log.info("Greeting successful.");
            healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingSuccess);
            return ResponseEntity.ok(String.format(template, greeting.getName()));
        } else {
            log.error("Greeting failed. No name given.");
            healthProcessStatisticData.addError("greeting", "ERROR400", "No name given.",
                    "Please check given request parameter 'name'.", greeting.toString());
            healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingFailed);
            return ResponseEntity.badRequest().body("400 - Bad request. No name given.");
        }
    }
}
