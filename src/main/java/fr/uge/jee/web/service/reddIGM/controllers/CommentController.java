package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.CommentRequestDto;
import fr.uge.jee.web.service.reddIGM.dto.CommentResponseDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.CommentService;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentRequestDto comment) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment, principal));
    }

    @GetMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.deleteComment(id, principal);
    }

    @PostMapping("/vote")
    public ResponseEntity<CommentResponseDto> voteForComment(@Valid @RequestBody VoteCommentDto vote) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.vote(vote, principal));
    }

    @GetMapping(value = {"/comment/{commentId}", "/comment/{commentId}/{orderType}"})
    public ResponseEntity<List<CommentResponseDto>> getSubCommentsOrdered(@PathVariable Long commentId, @PathVariable(required = false) OrderType orderType) {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            if (orderType == null){
               return ResponseEntity.status(HttpStatus.OK).body(commentService.getSubComments(commentId, OrderType.NEWEST));
            }
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getSubComments(commentId, orderType));
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (orderType == null){
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getSubComments(commentId, OrderType.NEWEST, principal));
        }
        System.out.println("odertype : " + orderType);
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getSubComments(commentId, orderType, principal));
    }

    @GetMapping(value = {"/post/{postId}", "/post/{postId}/{orderType}"})
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForPost(@PathVariable Long postId, @PathVariable(required = false) OrderType orderType) {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return orderType == null ? ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId, OrderType.NEWEST)) : ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId, orderType));
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderType == null ? ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId, OrderType.NEWEST, principal)) : ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId, orderType, principal));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForUser(@PathVariable String userName) {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForUser(userName));
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForUser(userName, principal));
    }
}
