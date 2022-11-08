package com.project.demo.service;

import com.project.model.Comments;
import com.project.model.Movie;
import com.project.model.User;
import com.project.repository.CommentaryRepository;
import com.project.service.CommentaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CommentaryServiceTest {

    private CommentaryRepository commentaryRepository;

    private CommentaryService commentaryService;

    @BeforeEach
    void setUp() {
        commentaryRepository = Mockito.mock(CommentaryRepository.class);

        commentaryService = new CommentaryService(commentaryRepository);
    }


    @Test
    void commentarySave(){
        Comments commentary = new Comments();
        commentary.setUser(new User());
        commentary.setMovie(new Movie());
        commentary.setText("some random text");

        boolean result = commentaryService.create(commentary);

        Assertions.assertTrue(result);;
        Mockito.verify(commentaryRepository).save(Mockito.any());
    }
}
