package com.sivalabs.springmongodbdemo.common;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoDBContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final MongoDBContainer mongoDBContainer;

    static {
        mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.2"));
        mongoDBContainer.start();
    }

    public static MongoDBContainer getMongoDBContainer() {
        return mongoDBContainer;
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
