package com.project.repository;

import com.project.model.Producer;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepository extends CrudRepository<Producer,String> {
}
