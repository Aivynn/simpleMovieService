package com.project.repository;

import com.project.model.Movie;
import com.project.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    boolean register(User visitor);

    Iterable<User> getAll();

    User findByUserName(String name);


    Optional<User> findById(String id);

    void delete(User id);

}
