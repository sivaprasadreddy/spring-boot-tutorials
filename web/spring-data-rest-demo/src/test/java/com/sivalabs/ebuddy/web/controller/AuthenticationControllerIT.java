package com.sivalabs.ebuddy.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser("admin@gmail.com")
    public void shouldReturnAuthenticatedUser() throws Exception {
        this.mvc
                .perform(get("/api/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Admin")));
    }
}
