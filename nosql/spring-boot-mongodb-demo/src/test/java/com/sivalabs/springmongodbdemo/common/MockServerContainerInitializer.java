package com.sivalabs.springmongodbdemo.common;

import org.mockserver.client.MockServerClient;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;

public class MockServerContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final MockServerContainer mockServerContainer;

    static {
        mockServerContainer = new MockServerContainer(DockerImageName.parse("jamesdbloom/mockserver:mockserver-5.5.4"));
        mockServerContainer.start();
    }

    public static MockServerContainer getMockServerContainer() {
        return mockServerContainer;
    }

    public static MockServerClient createMockServerClient() {
        final MockServerContainer mockServerContainer = MockServerContainerInitializer.getMockServerContainer();
        return new MockServerClient(
                mockServerContainer.getContainerIpAddress(),
                mockServerContainer.getServerPort());
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "mockserver.host=" + mockServerContainer.getContainerIpAddress(),
                "mockserver.port=" + mockServerContainer.getServerPort(),
                "myapp.githubBaseUrl=" + mockServerContainer.getEndpoint()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}