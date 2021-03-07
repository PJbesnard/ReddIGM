package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.PostService;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts/")
@AllArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest, ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/vote")
    public ResponseEntity<PostResponse> voteForPost(@Valid @RequestBody VotePostDto vote) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.vote(vote, principal));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

//    @GetMapping("by-subject/{id}")
//    public ResponseEntity<List<PostResponse>> getPostsBySubject(@PathVariable Long id) {
//        return status(HttpStatus.OK).body(postService.getPostsBySubjectId(id));
//    }

    @GetMapping("by-user/{id}")
    public ResponseEntity<List<PostResponse>> getPostsById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsById(id));
    }

    @GetMapping(value = {"all", "all/{orderType}"})
    public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable(required = false) OrderType orderType) {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return orderType == null ? ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts(OrderType.NEWEST)) : ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts(orderType));
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderType == null ? status(HttpStatus.OK).body(postService.getAllPosts(principal, OrderType.NEWEST)) : status(HttpStatus.OK).body(postService.getAllPosts(principal, orderType));
    }

    @GetMapping(value = {"by-subject/{id}", "/by-subject/{id}/{orderType}"})
    public ResponseEntity<List<PostResponse>> getAllPostsForSubject(@PathVariable Long id, @PathVariable(required = false) OrderType orderType) {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return orderType == null ? ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsForSubject(id, OrderType.NEWEST)) : ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsForSubject(id, orderType));
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderType == null ? ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsForSubject(id, OrderType.NEWEST, principal)) : ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsForSubject(id, orderType, principal));
    }

}
