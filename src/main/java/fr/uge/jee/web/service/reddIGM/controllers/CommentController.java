package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }
}
