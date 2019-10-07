package com.sivalabs.myservice.web.controllers;

import com.sivalabs.myservice.common.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Header;
import org.mockserver.verify.VerificationTimes;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GithubControllerIT extends AbstractIntegrationTest {

    @BeforeEach
    void setup() {
        mockServerClient.reset();
    }

    @Test
    void shouldGetGithubUserProfile() throws Exception {
        String username = "sivaprasadreddy";
        mockGetUserFromGithub(username);
        this.mockMvc.perform(get("/api/github/users/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is(username)))
                .andExpect(jsonPath("$.name", is("K. Siva Prasad Reddy")))
                .andExpect(jsonPath("$.public_repos", is(50)))
                ;
        verifyMockServerRequest("GET", "/users/.*", 1);
    }

    @Test
    void shouldGetDefaultUserProfileWhenFetchingFromGithubFails() throws Exception {
        mockGetUserFromGithubFailure();
        this.mockMvc.perform(get("/api/github/users/{username}", "dummy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("guest")))
                .andExpect(jsonPath("$.name", is("Guest")))
                .andExpect(jsonPath("$.public_repos", is(0)))
        ;
        verifyMockServerRequest("GET", "/users/.*", 1);
    }

    @Test
    void shouldGetDefaultUserProfileWhenFetchingFromGithubTimeout() throws Exception {
        mockGetUserFromGithubDelayResponse();
        this.mockMvc.perform(get("/api/github/users/{username}", "dummy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("guest")))
                .andExpect(jsonPath("$.name", is("Guest")))
                .andExpect(jsonPath("$.public_repos", is(0)))
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

    private void mockGetUserFromGithubDelayResponse() {
        mockServerClient.when(
                request().withMethod("GET").withPath("/users/.*"))
                .respond(response().withStatusCode(200).withDelay(TimeUnit.SECONDS, 10));
    }

    private void mockGetUserFromGithubFailure() {
        mockServerClient.when(
                request().withMethod("GET").withPath("/users/.*"))
                .respond(response().withStatusCode(404));
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
