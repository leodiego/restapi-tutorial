package com.tutorial.restapi.controller;

import com.tutorial.restapi.model.Person;
import com.tutorial.restapi.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value={"/api/person"})
public class PersonController {

    public static final String version = "1.0";

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping(value={"/version", "/version/"})
    public String version() {
        return version;
    }

    /**
     * Return all persons
     *
     * @return
     */
    @GetMapping(value={"/", ""})
    public ResponseEntity<List<Person>> get()
    {
        return ResponseEntity.ok(personRepository.findAll());
    }

    /**
     * Returns a person with id
     * @param id
     * @return
     */
    @GetMapping(value={"/{id}"})
    public ResponseEntity<Person> get(@PathVariable Long id)
    {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        }
//        return ResponseEntity.notFound().build();
        return new ResponseEntity("Can't find person with id " + id + " not found", HttpStatus.NOT_FOUND);
    }


    /**
     * Create a person and assign it a new Id
     * @param person
     * @return
     */
    @PostMapping(value={"", "/"})
    public ResponseEntity<Person> create (@RequestBody Person person) {
        Person p = personRepository.save(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).body(p);
    }

    /**
     * Delete a person with a particular Id
     * @param id
     */
    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Person> delete (@PathVariable Long id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            personRepository.deleteById(id);
            return new ResponseEntity(p, HttpStatus.OK);
        }
        return new ResponseEntity("Can't find person with id " + id, HttpStatus.NOT_FOUND);
    }

    /**
     * Update a person object with Id.
     *
     * @param id
     * @param person
     */
    @PutMapping(value={"/{id}"})
    public void update (@PathVariable Long id, @RequestBody Person person) {

        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            p.get().setName(person.getName());
            p.get().setAge(person.getAge());
            personRepository.save(p.get());
        }
    }


}
