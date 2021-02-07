package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentDto comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<Comment>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForUser(userName));
    }
}
