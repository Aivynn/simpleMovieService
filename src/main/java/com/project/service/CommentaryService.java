package com.project.service;

import com.project.model.Actor;
import com.project.model.Comments;
import com.project.repository.ActorRepository;
import com.project.repository.CommentaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryService {

    @Autowired
    private final CommentaryRepository commentaryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentaryService.class);

    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public Comments create(Comments comment) {

        Comments newComment = commentaryRepository.save(comment);
        LOGGER.info("Comment {} {} {} successfully saved", comment.getPerson(), comment.getText(),
                comment.getMovie().getName());
        return newComment;
    }


    public void delete(String id) {
        commentaryRepository.deleteById(id);
        LOGGER.info("Commentary with id {}, has been deleted", id);
    }


}
