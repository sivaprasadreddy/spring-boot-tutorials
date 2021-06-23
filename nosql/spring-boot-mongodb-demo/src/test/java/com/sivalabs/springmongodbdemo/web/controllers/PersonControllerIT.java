package com.sivalabs.springmongodbdemo.web.controllers;

import com.sivalabs.springmongodbdemo.common.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerIT extends BaseIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockServerClient.reset();
    }

    @Test
    void shouldGetAllPersons() throws Exception {
        this.mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    void shouldGetGithubUserProfile() throws Exception {
        String username = "sivaprasadreddy";
        mockGetUserFromGithub(username);
        this.mockMvc.perform(get("/api/persons/users/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is(username)))
                .andExpect(jsonPath("$.name", is("K. Siva Prasad Reddy")))
                .andExpect(jsonPath("$.public_repos", is(50)))
        ;
        verifyMockServerRequest("GET", "/users/.*", 1);
    }

    private void mockGetUserFromGithub(String username) {
        mockServerClient.when(
                request().withMethod("GET").withPath("/users/.*"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(json("{ " +
                                        "\"login\": \""+username+"\", " +
                                        "\"name\": \"K. Siva Prasad Reddy\", " +
                                        "\"public_repos\": 50 " +
                                        "}"))
                );
    }

    private void verifyMockServerRequest(String method, String path, int times) {
        mockServerClient.verify(
                request()
                        .withMethod(method)
                        .withPath(path),
                VerificationTimes.exactly(times)
        );
    }
}