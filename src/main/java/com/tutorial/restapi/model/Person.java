package com.tutorial.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    Long id;
    String name;
    String age;
}
