package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.VoteComment;
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
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }

    @PostMapping("/vote")
    public ResponseEntity<VoteCommentDto> voteForComment(@Valid @RequestBody VoteCommentDto vote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.vote(vote));
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<List<CommentDto>> getSubComments(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getSubComments(commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForUser(userName));
    }
}
