package com.sivalabs.springmongodbdemo.common;

import lombok.extern.slf4j.Slf4j;
import org.mockserver.client.MockServerClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MockServerContainer;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {MongoDBContainerInitializer.class, MockServerContainerInitializer.class})
public abstract class BaseIntegrationTest {

    protected MockServerClient mockServerClient = createMockServerClient();

    private MockServerClient createMockServerClient() {
        final MockServerContainer mockServerContainer = MockServerContainerInitializer.getMockServerContainer();
        return new MockServerClient(
                mockServerContainer.getContainerIpAddress(),
                mockServerContainer.getServerPort());
    }
}
