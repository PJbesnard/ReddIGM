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
import java.util.Objects;

@RestController
@Validated
@RequestMapping("/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long id) {
        return ResponseEntity.of(commentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentRequestDto comment) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment, principal));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.deleteComment(id, principal);
    }

    @PostMapping("/vote")
    public ResponseEntity<CommentResponseDto> voteForComment(@Valid @RequestBody VoteCommentDto vote) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.vote(vote, principal));
    }

    @GetMapping(value = {"/comment/{commentId}/{page}", "/comment/{commentId}/{page}/{orderType}"})
    public ResponseEntity<List<CommentResponseDto>> getSubCommentsOrdered(@PathVariable Long commentId, @PathVariable Integer page, @PathVariable(required = false) OrderType orderType) {
        List<CommentResponseDto> commentsDto;

        if (Objects.isNull(orderType) || orderType == OrderType.NEWEST) {
            commentsDto = commentService.getCommentsByParentSortedByDate(commentId, OrderType.DESCENDING, page);
        } else {
            commentsDto = commentService.getCommentsByParentSortedByScore(commentId, orderType, page);
        }

        return ResponseEntity.ok(commentsDto);
    }

    @GetMapping(value = {"/post/{postId}/{page}", "/post/{postId}/{page}/{orderType}"})
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForPost(@PathVariable Long postId, @PathVariable Integer page, @PathVariable(required = false) OrderType orderType) {
        List<CommentResponseDto> commentsDto;

        if (Objects.isNull(orderType) || orderType == OrderType.NEWEST) {
            commentsDto = commentService.getCommentsByParentSortedByDate(postId, OrderType.DESCENDING, page);
        } else {
            commentsDto = commentService.getCommentsByParentSortedByScore(postId, orderType, page);
        }

        return ResponseEntity.ok(commentsDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForUser(@PathVariable long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserSortedByDate(userId, OrderType.ASCENDING));
    }
}
