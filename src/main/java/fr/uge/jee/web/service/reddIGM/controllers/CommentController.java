package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.model.Comment;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {
    private final CommentRepo repository;

    public CommentController(CommentRepo repository) {
        this.repository = repository;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        comment.setCreationDate(LocalDateTime.now());
        return repository.save(comment);
    }
}
