package com.project.controller;

import com.project.dto.UserDto;
import com.project.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.BindingResult;
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
        modelAndView.addObject("visitor", new UserDto());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView registerUser(@ModelAttribute UserDto visitor, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/register");
        }
        if(!(visitor.getPassword().equals(visitor.getMatchingPassword()))){
            return new ModelAndView("redirect:/register");
        }
        userService.register(visitor);
        return modelAndView;
    }
}
