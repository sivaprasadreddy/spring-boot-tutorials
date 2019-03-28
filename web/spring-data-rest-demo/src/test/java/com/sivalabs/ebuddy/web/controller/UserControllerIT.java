package com.sivalabs.ebuddy.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        setupTestData();
    }

    @After
    public void tearDown() {
        cleanupTestData();
    }

    @Test
    public void should_get_all_users() throws Exception {
        this.mvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        this.mvc.perform(get("/api/users/" + existingUser.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(newUser);
        this.mvc.perform(post("/api/users")
                .content(userJson)
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_update_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(updateUser);
        this.mvc.perform(put("/api/users/" + updateUser.getId())
                .content(userJson)
                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_delete_user() throws Exception {
        this.mvc.perform(delete("/api/users/" + existingUser.getId()))
                .andExpect(status().isNoContent());
    }
}
