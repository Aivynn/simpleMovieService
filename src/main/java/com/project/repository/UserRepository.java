package com.project.repository;

import com.project.model.Movie;
import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String name);

    Optional<Movie> findByEmail(String mail);
}

