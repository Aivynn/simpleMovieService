package com.project.repository;

import com.project.model.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends CrudRepository<Comments, String> {
}
