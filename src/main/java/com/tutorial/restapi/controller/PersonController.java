package com.tutorial.restapi.controller;

import com.tutorial.restapi.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value={"/api/person"})
public class PersonController {

    public static final String version = "1.0";

    private ArrayList<Person> personArrayList = new ArrayList<>();
    private Long id = 1L;


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
    public ResponseEntity<List<Person>> get() {
        return ResponseEntity.ok(personArrayList);
    }

    /**
     * Returns a person with id
     * @param id
     * @return
     */
    @GetMapping(value={"/{id}"})
    public ResponseEntity<Person> get(@PathVariable Long id) {
        for (Person p : personArrayList) {
            if (p.getId() == id) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Create a person and assign it a new Id
     * @param person
     * @return
     */
    @PostMapping(value={"", "/"})
    public ResponseEntity<Person> create (@RequestBody Person person) {
        person.setId(id++);
        personArrayList.add(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(location).body(person);
    }

    /**
     * Delete a person with a particular Id
     * @param id
     */
    @DeleteMapping(value={"/{id}"})
    public void delete (@PathVariable Long id) {
        Iterator<Person> personIterator = personArrayList.iterator();
        while (personIterator.hasNext()) {
            if (personIterator.next().getId() == id) {
                personIterator.remove();
            }
        }
    }

    /**
     * Update a person object with Id.
     *
     * @param id
     * @param person
     */
    @PutMapping(value={"/{id}"})
    public void update (@PathVariable Long id, @RequestBody Person person) {
        Iterator<Person> personIterator = personArrayList.iterator();
        while (personIterator.hasNext()) {
            Person p = personIterator.next();
            if (p.getId() == id) {
                p.setAge(person.getAge());
                p.setName(person.getName());
                break;
            }
        }
    }


}
