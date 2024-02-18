package com.iksgmbh.actuator.health.sample.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreetingTest {

    @Test
    void isValid_nameGiven_test() {

        // given
        Greeting greeting = new Greeting(1L, "Mustermann");

        // when
        boolean result = greeting.isValid();

        // then
        assertTrue(result);
    }

    @Test
    void isValid_noNameGiven_test() {

        // given
        Greeting greeting = new Greeting(1L, "");

        // when
        boolean result = greeting.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void isValid_null_test() {

        // given
        Greeting greeting = new Greeting(1L, null);

        // when
        boolean result = greeting.isValid();

        // then
        assertFalse(result);
    }
}