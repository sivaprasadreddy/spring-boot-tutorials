package com.sivalabs.springmongodbdemo.repositories;

import com.sivalabs.springmongodbdemo.documents.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

}
