package com.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;

    @NotBlank
    @Size(min = 5, max = 15)
    private String name;
    @NotBlank
    @Size(min = 15, max = 40)
    private String title;

    private int budget;

    private Boolean status;

    private int fees;
    @NotBlank
    private String posterUrl;
    @NotBlank
    @Size(min = 5, max = 15)
    private String genre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors = new ArrayList<>();

    @ManyToOne(optional = false,cascade=CascadeType.ALL)
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();


}
