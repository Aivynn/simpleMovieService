package com.project.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;

    private String person;

    private Boolean status;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    private Movie movie;
}
