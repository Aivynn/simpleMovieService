package com.project.mapper;

import com.project.dto.MovieDto;
import com.project.model.Movie;

public class movieMapper {

    public static Movie movieMap(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setActors(movieDto.getActors());
        movie.setFees(Integer.parseInt(movieDto.getFees()));
        movie.setBudget(Integer.parseInt(movieDto.getBudget()));
        movie.setProducer(movieDto.getProducer());
        movie.setName(movieDto.getName());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setGenre(movieDto.getGenre());
        movie.setTitle(movieDto.getTitle());
        movie.setStatus(true);

        return movie;
    }
}
