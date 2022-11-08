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

    public Boolean create(Comments comment) {

        if (comment.getText().length() < 5) {
            return false;
        }
        commentaryRepository.save(comment);
        LOGGER.info("Commentary {} {} has been saved", comment.getUser().getUsername(), comment.getText());
        return true;
    }


    public void delete(String id) {
        commentaryRepository.deleteById(id);
        LOGGER.info("Commentary with id {}, has been deleted", id);
    }
}
