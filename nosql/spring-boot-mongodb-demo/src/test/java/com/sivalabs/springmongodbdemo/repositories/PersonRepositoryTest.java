package com.sivalabs.springmongodbdemo.repositories;

import com.sivalabs.springmongodbdemo.common.BaseMongoDBTest;
import com.sivalabs.springmongodbdemo.documents.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class PersonRepositoryTest extends BaseMongoDBTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void name() {
        personRepository.findAll();
        Person p1 = new Person();
        p1.setEmail("a@mail.com");
        final Person person1 = personRepository.save(p1);
        System.out.println(person1);
        final Optional<Person> optionalPerson = personRepository.findById(person1.getId());
        System.out.println(optionalPerson.orElse(null));
    }
}