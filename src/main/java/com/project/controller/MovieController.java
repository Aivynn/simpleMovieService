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

    @GetMapping("")
    public ModelAndView getOnePage(ModelAndView model, @DefaultValue("1") @RequestParam(value = "page", required = false) int currentPage) {
        Page<Movie> page = movieService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Movie> movies = page.getContent();

        model = paginationModel(model, currentPage, page.getNumber(), totalPages, totalItems, movies,null);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addObject("pageNumbers", pageNumbers);

        }

        return model;
    }

    private static ModelAndView paginationModel(ModelAndView model, int currentPage, int page, int totalPages, long totalItems, List<Movie> movies,String searchWord) {
        String pageWord = "/movies/search?page=";
        model.addObject("currentPage", currentPage);
        model.addObject("totalPages", totalPages);
        model.addObject("totalItems", totalItems);
        model.addObject("movies", movies);
        model.addObject("number", page);
        model.addObject("pageWord",pageWord);
        model.addObject("searchWord", searchWord);
        model.setViewName("index");
        return model;
    }


    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable(value = "id") String id, ModelAndView model) {
        Movie movie = movieService.findById(id).get();
        model.addObject("movie", movie);
        model.addObject("comment", new Comments());
        model.setViewName("moviedetail");
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/addComment")
    public ModelAndView createComment(@PathVariable(value = "id") String id, @ModelAttribute Comments comment) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUserName(username);
        comment.setMovie(movieService.findById(id).get());
        comment.setUser(user);
        commentaryService.create(comment);
        return new ModelAndView("redirect:/movies/{id}");
    }

    @GetMapping("/search")
    public ModelAndView search(ModelAndView model,
                               @RequestParam(value = "searchWord") String searchWord) {

        int totalPages = 1;
        long totalItems = 8;
        List<Movie> movies = movieService.findPage(searchWord);

        model = paginationModel(model, 1, 1, totalPages, totalItems, movies, searchWord);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addObject("pageNumbers", pageNumbers);

        }
        return model;
    }

}

