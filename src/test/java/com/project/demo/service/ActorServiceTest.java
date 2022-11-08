package com.project.demo.service;

import com.project.model.Actor;
import com.project.repository.ActorRepository;
import com.project.service.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ActorServiceTest {

    private ActorRepository actorRepository;

    private ActorService actorService;



    @BeforeEach
    void setUp() {
        actorRepository = Mockito.mock(ActorRepository.class);

        actorService = new ActorService(actorRepository);
    }

    @Test
    void ActorSave() {
        Actor actor = new Actor();
        actor.setAge(54);
        actor.setFirstname("Valeriy");
        actor.setLastname("Bevs");
        actor.setAchievements("Oscar 2022");

        boolean result = actorService.create(actor);

        Assertions.assertTrue(result);;
        Mockito.verify(actorRepository).save(Mockito.any());
    }
    @Test
    void ActorSaveFail() {
        Actor actor = new Actor();
        actor.setAge(54);
        actor.setFirstname("Val");
        actor.setLastname("Bevs");
        actor.setAchievements("Oscar 2022");

        boolean result = actorService.create(actor);

        Assertions.assertFalse(result);;
    }
}
