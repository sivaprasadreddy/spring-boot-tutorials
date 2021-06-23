package com.sivalabs.springmongodbdemo.web.controllers;

import com.sivalabs.springmongodbdemo.services.GithubService;
import com.sivalabs.springmongodbdemo.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @MockBean
    PersonService personService;

    @MockBean
    GithubService githubService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetAllPersons() throws Exception {
        given(personService.getAllPersons()).willReturn(List.of());

        this.mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(0)));
    }
}