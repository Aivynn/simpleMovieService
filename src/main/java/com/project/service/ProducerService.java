package com.project.service;

import com.project.model.Actor;
import com.project.model.Producer;
import com.project.repository.ActorRepository;
import com.project.repository.ProducerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    @Autowired
    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository){
        this.producerRepository = producerRepository;
    }
    public Boolean create(Producer producer){
        if (producer.getFirstname().length() < 5 || producer.getLastname().length() < 4) {
            return false;
        }
        producerRepository.save(producer);
        LOGGER.info("Producer {} {} has been saved", producer.getFirstname(), producer.getLastname());
        return true;
    }

    public Iterable<Producer> findAll(){
        return producerRepository.findAll();
    }
}
