package com.project.service;

import com.project.model.Movie;
import com.project.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    public MovieRepository movieRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 8);
        return movieRepository.findAll(pageable);
    }

    public List<Movie> findPage(String name) {
        return movieRepository.findByNameContaining(name);
    }


    public boolean createMovie(Movie movie) {
        if (movie.getProducer() == null) {
            return false;
        }
        movieRepository.save(movie);
        LOGGER.info("Movie {} , {} has been saved", movie.getName(), movie.getGenre());
        return true;
    }

    public Optional<Movie> findById(String id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public Movie update(String id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Can't find movie by id: " + id);
        });
        existingMovie.setActors(movie.getActors());
        existingMovie.setFees(movie.getFees());
        existingMovie.setBudget(movie.getBudget());
        existingMovie.setProducer(movie.getProducer());
        existingMovie.setName(movie.getName());
        existingMovie.setPosterUrl(movie.getPosterUrl());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setStatus(movie.getStatus());
        LOGGER.info("Movie {} has been updated", movie.getName());
        movieRepository.save(existingMovie);
        return existingMovie;
    }

}
