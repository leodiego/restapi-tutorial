package com.tutorial.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    @Size(min=3, max=30)
    String name;

    @NotBlank
    String age;
}
