package com.project.controller;

import com.project.model.Actor;
import com.project.model.Movie;
import com.project.model.Producer;
import com.project.service.ActorService;
import com.project.service.CommentaryService;
import com.project.service.MovieService;
import com.project.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String main() {
        return """
                DemoApplication<br>
                <a href="http://localhost:8080/admin/createmovie">add Movie</a><br>
                <a href="http://localhost:8080/admin/createactor">add actor</a><br>
                <a href="http://localhost:8080/admin/createproducer">add producer</a><br>
                """;
    }

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final CommentaryService commentaryService;

    @Autowired
    private final ActorService actorService;

    @Autowired
    private final ProducerService producerService;

    public AdminController(MovieService movieService, CommentaryService commentaryService, ActorService actorService, ProducerService producerService) {
        this.movieService = movieService;
        this.commentaryService = commentaryService;
        this.actorService = actorService;
        this.producerService = producerService;
    }

    @RequestMapping(value = "/createactor", method = RequestMethod.GET)
    public ModelAndView getUsers(ModelAndView model) {
        model.addObject("actor", new Actor());
        model.setViewName("createactor");
        return model;
    }

    @RequestMapping(value = "/createactor", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Actor actor) {
        Actor movie = actorService.create(actor);
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
        model.addObject("movie", new Movie());
        model.addObject("actors", actors);
        model.addObject("producers", producers);
        model.setViewName("createmovie");
        return model;
    }

    @RequestMapping(value = "/createmovie", method = RequestMethod.POST)
    public String createmovie(@ModelAttribute Movie movie) {
        movieService.createMovie(movie);
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
        return new ModelAndView("redirect:/movies");
    }
}
