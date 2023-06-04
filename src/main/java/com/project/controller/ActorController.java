package com.project.controller;

import com.project.model.Actor;
import com.project.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable(value = "id") String id, ModelAndView model) {
        Actor actor = actorService.findById(id).get();
        model.addObject("actor", actor);
        model.setViewName("actordetails");
        return model;
    }
}
