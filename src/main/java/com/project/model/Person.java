package com.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;


    private String firstname;

    private String lastname;

    private int age;

    private String achievements;

    public Person(String firstname, String lastname, int age, String achievements) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.achievements = achievements;
    }
}
