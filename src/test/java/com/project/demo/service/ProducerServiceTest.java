package com.project.demo.service;

import com.project.model.Producer;
import com.project.repository.ProducerRepository;
import com.project.service.ProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProducerServiceTest {

    private ProducerRepository producerRepository;

    private ProducerService producerService;



    @BeforeEach
    void setUp() {
        producerRepository = Mockito.mock(ProducerRepository.class);

        producerService = new ProducerService(producerRepository);
    }

    @Test
    void ProducerSave() {
        Producer producer = new Producer();
        producer.setAge(36);
        producer.setFirstname("Christopher");
        producer.setLastname("Nolan");
        producer.setAchievements("Oscar 2015");

        boolean result = producerService.create(producer);

        Assertions.assertTrue(result);;
        Mockito.verify(producerRepository).save(Mockito.any());
    }
    @Test
    void ProducerSaveFail() {
        Producer producer = new Producer();
        producer.setAge(36);
        producer.setFirstname("Chri");
        producer.setLastname("Nolan");
        producer.setAchievements("Oscar 2015");

        boolean result = producerService.create(producer);

        Assertions.assertFalse(result);;
    }
}
