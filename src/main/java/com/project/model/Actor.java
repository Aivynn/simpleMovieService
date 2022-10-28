package com.project.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
public class Actor extends Person{
    @ManyToMany
    private List<Movie> movieList = new ArrayList<>();

    public Actor(String firstname,String lastname,int age,String achievements){
        super(firstname,lastname,age,achievements);

    }
}
