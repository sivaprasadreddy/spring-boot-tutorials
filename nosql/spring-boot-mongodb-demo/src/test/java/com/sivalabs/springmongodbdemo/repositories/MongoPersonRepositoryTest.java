package com.sivalabs.springmongodbdemo.repositories;

import com.sivalabs.springmongodbdemo.common.MongoDBContainerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ContextConfiguration(initializers = {MongoDBContainerInitializer.class})
class MongoPersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void name() {
        personRepository.findAll();
    }
}