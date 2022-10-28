package com.project.controller;

import com.project.model.User;
import com.project.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/register")
public class UserRegisterController {


    private final UserServiceImpl userService;

    @Autowired
    public UserRegisterController(@Lazy UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.addObject("visitor", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView registerUser(@ModelAttribute User visitor) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userService.register(visitor);
        System.out.println(visitor);
        return modelAndView;
    }
}
