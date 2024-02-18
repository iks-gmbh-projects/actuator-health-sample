package com.iksgmbh.actuator.health.sample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void greeting_ok_test() throws Exception {

        // given

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting?name=Mustermann")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hallo Mustermann, wie geht's?"));
    }

    @Test
    void greeting_unauthorized_test() throws Exception {

        // given

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting?name=Mustermann"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void greeting_badrequest_test() throws Exception {

        // given

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting?name=")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
