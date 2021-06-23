package com.sivalabs.springmongodbdemo.services;

import com.sivalabs.springmongodbdemo.documents.Person;
import com.sivalabs.springmongodbdemo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final MongoOperations mongoOperations;


    public List<Person> getAllPersons() {
        return personRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public void createPerson(Person person) {
        personRepository.save(person);
    }
}
