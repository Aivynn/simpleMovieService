package com.project.demo.service;

import com.project.model.Movie;
import com.project.model.Producer;
import com.project.repository.MovieRepository;
import com.project.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieServiceTest {

    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
         movieRepository = Mockito.mock(MovieRepository.class);

         movieService = new MovieService(movieRepository);
    }

    @Test
    void MovieTestSave() {
        Movie movie = new Movie();
        movie.setName("movie1337");
        movie.setTitle("something very cool");
        movie.setGenre("Action");
        movie.setProducer(new Producer());
        movie.setActors(List.of());
        movie.setPosterUrl("no url");
        movie.setBudget(1);

        boolean result = movieService.createMovie(movie);

        Assertions.assertTrue(result);;
        Mockito.verify(movieRepository).save(Mockito.any());
    }
    @Test
    void MovieTestFailSave() {
        Movie movie = new Movie();
        movie.setName("movie1337");
        movie.setTitle("something very cool");
        movie.setGenre("Action");
        movie.setActors(List.of());
        movie.setPosterUrl("no url");
        movie.setBudget(1);

        boolean result = movieService.createMovie(movie);

        Assertions.assertFalse(result);
    }
}


