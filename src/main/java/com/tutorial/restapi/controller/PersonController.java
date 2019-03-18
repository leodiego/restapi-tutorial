package com.tutorial.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/person"})
public class PersonController {

    public static final String version = "1.0";


    @GetMapping(value={"/version", "/version/"})
    public String version() {
        return version;
    }
}
