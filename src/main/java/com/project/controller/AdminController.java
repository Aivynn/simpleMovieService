package com.project.controller;

import com.project.dto.MovieDto;
import com.project.dto.UserDto;
import com.project.mapper.movieMapper;
import com.project.model.*;
import com.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final CommentaryService commentaryService;

    @Autowired
    private final ActorService actorService;

    @Autowired
    private final ProducerService producerService;

    @Autowired
    private final UserServiceImpl userService;

    public AdminController(MovieService movieService, CommentaryService commentaryService, ActorService actorService, ProducerService producerService, UserServiceImpl userService) {
        this.movieService = movieService;
        this.commentaryService = commentaryService;
        this.actorService = actorService;
        this.producerService = producerService;
        this.userService = userService;
    }

    @RequestMapping(value = "/createactor", method = RequestMethod.GET)
    public ModelAndView getUsers(ModelAndView model) {
        model.addObject("actor", new Actor());
        model.setViewName("createactor");
        return model;
    }

    @RequestMapping(value = "/createactor", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Actor actor) {
        actorService.create(actor);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/createproducer", method = RequestMethod.GET)
    public ModelAndView producer(ModelAndView model) {
        model.addObject("producer", new Producer());
        model.setViewName("createproducer");
        return model;
    }

    @RequestMapping(value = "/createproducer", method = RequestMethod.POST)
    public String createproducer(@ModelAttribute Producer producer) {
        producerService.create(producer);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/createmovie", method = RequestMethod.GET)
    public ModelAndView movie(ModelAndView model) {
        Iterable<Actor> actors = actorService.findAll();
        Iterable<Producer> producers = producerService.findAll();
        model.addObject("movie", new MovieDto());
        model.addObject("actors", actors);
        model.addObject("producers", producers);
        model.setViewName("createmovie");
        return model;
    }

    @RequestMapping(value = "/createmovie", method = RequestMethod.POST)
    public String createmovie(@ModelAttribute MovieDto movie) {
        movieService.createMovie(movieMapper.movieMap(movie));
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editMovie(@PathVariable("id") String id, ModelAndView model) {
        Movie movie = movieService.findById(id).get();
        model.addObject("movie", movie);
        model.addObject("actors", movie.getActors());
        model.addObject("producers", movie.getProducer());
        model.setViewName("edit");
        return model;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editMovie(@PathVariable("id") String id, @Valid @ModelAttribute("movie") Movie movie) {
        Movie updatedMovie = movieService.update(id, movie);
        return new ModelAndView("redirect:/movies?page=1");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComment(@PathVariable("id") String id) {
        commentaryService.delete(id);
        return new ModelAndView("redirect:/movies?page=1");
    }

    @GetMapping("/ban/{id}")
    public ModelAndView banUser(@PathVariable("id") String id) {
        Optional<User> user = userService.findById(id);
        user.ifPresent(userService::ban);
        return new ModelAndView("redirect:/movies?page=1");
    }


    @GetMapping("/manager")
    public ModelAndView adminPanel(ModelAndView model) {
        Iterable<User> users = userService.getAll();
        ;
        model.addObject("users", users);
        model.addObject("visitor", new UserDto());
        model.setViewName("panel");
        return model;
    }

    @PostMapping("/manager/{id}")
    public ModelAndView privileges(@PathVariable(value = "id") String id, @ModelAttribute("visitor") UserDto user, ModelAndView model) {
        userService.update(user, id);
        return new ModelAndView("redirect:/admin/manager");
    }
}
