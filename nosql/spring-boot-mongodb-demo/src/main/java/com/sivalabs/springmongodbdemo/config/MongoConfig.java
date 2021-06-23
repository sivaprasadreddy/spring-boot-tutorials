
package com.sivalabs.springmongodbdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivalabs.springmongodbdemo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoAuditing
@RequiredArgsConstructor
public class MongoConfig {
    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean factory) {
        return new ValidatingMongoEventListener(factory);
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        personRepository.deleteAll();

        Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
        factoryBean.setMapper(objectMapper);
        factoryBean.setResources(new Resource[]{new ClassPathResource("people-sample.json")});

        return factoryBean;
    }
}
