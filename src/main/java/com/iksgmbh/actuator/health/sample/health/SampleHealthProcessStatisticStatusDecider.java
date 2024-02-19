package com.iksgmbh.actuator.health.sample.health;

import com.iksgmbh.actuator.health.procstat.healthindicator.HealthProcessStatisticStatusDecider;
import com.iksgmbh.actuator.health.procstat.model.HealthProcessStatisticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.LinkedHashSet;

/**
 * Health process statistic endpoint for in memory process statistic data -
 * health indicator implementation for {@link com.iksgmbh.actuator.health.procstat.healthindicator.HealthProcessStatisticHealthIndicator}
 *
 * @version 1.6 2023-10-02
 */
@Component
@Primary
public class SampleHealthProcessStatisticStatusDecider extends HealthProcessStatisticStatusDecider {

    /**
     * Constructor.
     */
    @Autowired
    public SampleHealthProcessStatisticStatusDecider(final HealthProcessStatisticData healthProcessStatisticData) {
        super(healthProcessStatisticData);
        init();
    }

    /**
     * Initialize health process statistic data with replaced default key list.
     */
    private void init() {
        LinkedHashSet<String> defaultStatisticDataKeyList = new LinkedHashSet<>();
        for (SampleHealthProcessStatisticDataKey healthProcessStatisticDataKey : EnumSet.allOf(SampleHealthProcessStatisticDataKey.class)) {
            defaultStatisticDataKeyList.add(healthProcessStatisticDataKey.toString());
        }
        defaultStatisticDataKeyList.add(SampleHealthProcessStatisticDataKey.error.toString());  // default data key "error", used by method addError()

        healthProcessStatisticData.setDefaultStatisticDataKeyList(defaultStatisticDataKeyList);
        healthProcessStatisticData.resetStatisticData();
    }

    /**
     * Implementation to determine current application health status like
     * UP, DOWN, OUT_OF_SERVICE, UNKNOWN, ERROR, WARNING, etc.
     * @see org.springframework.boot.actuate.health.Health
     *
     * @return Health status object
     */
    @Override
    public Health checkHealth() {

        // DOWN - Errors occurred
        if (isErrorTimestampGreaterSuccessTimestamp(SampleHealthProcessStatisticDataKey.greetingFailed,
                                                    SampleHealthProcessStatisticDataKey.greetingSuccess)) {
            // || isErrorTimestampGreaterSuccessTimestamp(SampleHealthProcessStatisticDataKey.function2Failed,
            //                                            SampleHealthProcessStatisticDataKey.function2Success)
            // || isErrorTimestampGreaterSuccessTimestamp(SampleHealthProcessStatisticDataKey.function3Failed,
            //                                            SampleHealthProcessStatisticDataKey.function3Success)) {
            return Health.down()
                    .withDetails(healthProcessStatisticData.getHealthProcessStatisticDataMap())
                    .build();
        }

        // WARNING - Errors occurred in the past
        if (isCounterGreaterZero(SampleHealthProcessStatisticDataKey.error)) {
            return Health.status(HEALTH_STATUS_CODE_WARNING)
                    .withDetails(healthProcessStatisticData.getHealthProcessStatisticDataMap())
                    .build();
        }

        // UP - Everything ok
        return Health.up()
                .withDetails(healthProcessStatisticData.getHealthProcessStatisticDataMap())
                .build();
    }
}
