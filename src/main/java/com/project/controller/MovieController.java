package com.project.controller;

import com.project.model.Comments;
import com.project.model.Movie;
import com.project.model.User;
import com.project.service.CommentaryService;
import com.project.service.MovieService;
import com.project.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final CommentaryService commentaryService;

    @Autowired
    private final UserServiceImpl userService;

    public MovieController(MovieService movieService, CommentaryService commentaryService, UserServiceImpl userService) {
        this.movieService = movieService;
        this.commentaryService = commentaryService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        String test = "test";
        return test;

    }

    @GetMapping("")
    public ModelAndView getOnePage(ModelAndView model, @DefaultValue("1") @RequestParam(value = "page", required = false) int currentPage) {
        Page<Movie> page = movieService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Movie> movies = page.getContent();

        model.addObject("currentPage", currentPage);
        model.addObject("totalPages", totalPages);
        model.addObject("totalItems", totalItems);
        model.addObject("movies", movies);
        model.addObject("number", page.getNumber());
        model.setViewName("index");

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addObject("pageNumbers", pageNumbers);

        }

        return model;
    }


    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable(value = "id") String id, ModelAndView model) {
        Movie movie = movieService.findById(id).get();
        model.addObject("movie", movie);
        model.addObject("comment", new Comments());
        model.setViewName("movie_detail");
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/addComment")
    public ModelAndView createComment(@PathVariable(value = "id") String id, @ModelAttribute Comments comment, @ModelAttribute Movie movie) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUserName(username);
        ModelAndView modelAndView = new ModelAndView("redirect:/movies/{id}");
        comment.setMovie(movieService.findById(id).get());
        comment.setUser(user);
        commentaryService.create(comment);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(ModelAndView model,
                               @DefaultValue("1")
                               @RequestParam(value = "searchPage", required = false) Integer currentPage,
                               @RequestParam(value = "searchWord") String searchWord) {

        int totalPages = 1;
        long totalItems = 8;
        String pageForWhat = "/movies/search?page=";
        List<Movie> movies = movieService.findPage(searchWord);

        model.addObject("currentPage", currentPage);
        model.addObject("totalPages", totalPages);
        model.addObject("totalItems", totalItems);
        model.addObject("movies", movies);
        model.addObject("pageForWhat", pageForWhat);
        model.addObject("number", 1);
        model.addObject("searchWord", searchWord);

        model.setViewName("index");

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addObject("pageNumbers", pageNumbers);

        }

        return model;
    }

}

