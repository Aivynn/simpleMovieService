package com.project.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
public class Producer extends Person {

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();

    public Producer(String firstname,String lastname,int age,String achievements){
        super(firstname,lastname,age,achievements);

    }
}
