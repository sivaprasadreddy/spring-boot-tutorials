package com.sivalabs.springmongodbdemo.web.controllers;

import com.sivalabs.springmongodbdemo.documents.Person;
import com.sivalabs.springmongodbdemo.models.GithubUser;
import com.sivalabs.springmongodbdemo.services.GithubService;
import com.sivalabs.springmongodbdemo.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final PersonService personService;
    private final GithubService githubService;

    @GetMapping("")
    public List<Person> allPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("")
    public void createPerson(@RequestBody Person person) {
        personService.createPerson(person);
    }

    @GetMapping("/users/{username}")
    public GithubUser getGithubUserProfile(@PathVariable String username) {
        return githubService.getGithubUserProfile(username);
    }

}
