package com.project.service;

import com.project.dto.UserDto;
import com.project.model.Role;
import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.repository.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public boolean register(UserDto visitor) {
        User user = new User();
        if (userRepository.findByEmail(visitor.getEmail()).isPresent()) {
            return false;
        }
        user.setUsername(visitor.getUsername());
        user.setEmail(visitor.getEmail());
        user.setPassword(passwordEncoder.encode(visitor.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(true);
        userRepository.save(user);
        LOGGER.info("User {} {} {} successfully saved", user.getUsername(), user.getEmail(),
                user.getRole());
        return true;
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(User id) {
        userRepository.delete(id);
        LOGGER.info("User with id {} has been deleted", id);
    }

    @Override
    public void ban(User user) {
        user.setStatus(false);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.getStatus()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRole().getAuthorities());
    }

    public boolean update(UserDto userDto, String id) {
       User user = userRepository.findById(id).get();
       if(user.getRole() != null) {
           user.setRole(userDto.getRole());
           userRepository.save(user);
       }
        if(user.getStatus() != null) {
            user.setStatus(userDto.getStatus());
            userRepository.save(user);
        }
       return true;
    }

}
