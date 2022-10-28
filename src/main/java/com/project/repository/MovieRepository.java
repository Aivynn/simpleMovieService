package com.project.repository;

import com.project.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByNameContaining(String name);

    Page<Movie> findAll(Pageable pageable);

}
