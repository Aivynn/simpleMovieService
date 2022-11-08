package com.project.dto;

import com.project.model.Actor;
import com.project.model.Comments;
import com.project.model.Producer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDto {

    private String name;

    private String title;

    private String budget;

    private Boolean status;


    private String fees;

    private String posterUrl;
    private String genre;

    private List<Actor> actors = new ArrayList<>();

    private Producer producer;

}
