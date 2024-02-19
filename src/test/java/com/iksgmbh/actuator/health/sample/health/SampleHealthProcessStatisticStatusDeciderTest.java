package com.iksgmbh.actuator.health.sample.health;

import com.iksgmbh.actuator.health.procstat.config.HealthProcessStatisticConfig;
import com.iksgmbh.actuator.health.procstat.model.HealthProcessStatisticData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {
        HealthProcessStatisticConfig.class,
        SampleHealthProcessStatisticStatusDecider.class
})
public class SampleHealthProcessStatisticStatusDeciderTest {

    @Autowired
    private HealthProcessStatisticData healthProcessStatisticData;

    @Autowired
    private SampleHealthProcessStatisticStatusDecider healthProcessStatisticStatusDecider;


    @Test
    void checkHealth_UP_test() {

        // given
        healthProcessStatisticData.reset();

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("UP", result.getStatus().toString());
    }

    @Test
    void checkHealth_WARNING_test() {

        // given
        healthProcessStatisticData.reset();
        healthProcessStatisticData.addError("function", "returncode", "messagetext", "instructiontext", "referenceid");

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("WARNING", result.getStatus().toString());
    }

    @Test
    void checkHealth_DOWN_test() {

        // given
        healthProcessStatisticData.reset();
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingFailed);

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("DOWN", result.getStatus().toString());
    }


    @Test
    void checkHealth_greetingRequest_test() {

        // given
        healthProcessStatisticData.reset();
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingRequest);

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("UP", result.getStatus().toString());
    }

    @Test
    void checkHealth_greetingSuccess_test() {

        // given
        healthProcessStatisticData.reset();
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingRequest);
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingSuccess);

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("UP", result.getStatus().toString());
    }

    @Test
    void checkHealth_greetingFailed_test() {

        // given
        healthProcessStatisticData.reset();
        healthProcessStatisticData.incrementCounter(SampleHealthProcessStatisticDataKey.greetingFailed);

        // when
        Health result = healthProcessStatisticStatusDecider.checkHealth();

        // then
        assertEquals("DOWN", result.getStatus().toString());  // WARNING
    }
}
