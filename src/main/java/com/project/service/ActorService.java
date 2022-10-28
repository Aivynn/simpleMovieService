package com.project.service;

import com.project.model.Actor;
import com.project.model.Movie;
import com.project.repository.ActorRepository;
import com.project.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }
    public Actor create(Actor actor){
        return actorRepository.save(actor);
    }

    public Iterable<Actor> findAll(){
        return actorRepository.findAll();
    }

}
