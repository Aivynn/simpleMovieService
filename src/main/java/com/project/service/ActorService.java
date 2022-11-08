package com.project.service;

import com.project.model.Actor;
import com.project.repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    @Autowired
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }
    public Boolean create(Actor actor){
        if (actor.getFirstname().length() < 5 || actor.getLastname().length() < 4) {
            return false;
        }
        actorRepository.save(actor);
        LOGGER.info("Actor {} {} has been saved", actor.getFirstname(), actor.getLastname());
        return true;
    }

    public Iterable<Actor> findAll(){
        return actorRepository.findAll();
    }

}
