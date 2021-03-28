package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.PostService;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postService.deletePost(id, principal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.of(postService.getPost(id));
    }

    @GetMapping("by-user/{id}")
    public ResponseEntity<List<PostResponse>> getPostsById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostsByUserSortedByDate(id, OrderType.DESCENDING));
    }

    @GetMapping(value = {"all/{page}", "all/{page}/{orderType}"})
    public ResponseEntity<List<PostResponse>> getAllPosts(@PathVariable Integer page, @PathVariable(required = false) OrderType orderType) {
        List<PostResponse> postResponses;

        if (Objects.isNull(orderType) || orderType == OrderType.NEWEST) {
            postResponses = postService.getAllPostsSortedByDate(OrderType.DESCENDING, page);
        } else {
            postResponses = postService.getAllPostsSortedByScore(orderType, page);
        }

        return ResponseEntity.ok(postResponses);
    }

    @GetMapping(value = {"by-subject/{id}/{page}", "/by-subject/{id}/{page}/{orderType}"})
    public ResponseEntity<List<PostResponse>> getAllPostsForSubject(@PathVariable Long id, @PathVariable Integer page, @PathVariable(required = false) OrderType orderType) {
        List<PostResponse> postResponses;

        if (Objects.isNull(orderType) || orderType == OrderType.NEWEST) {
            postResponses = postService.getPostsBySubjectSortedByDate(id, OrderType.DESCENDING, page);
        } else {
            postResponses = postService.getPostsBySubjectSortedByScore(id, orderType, page);
        }

        return ResponseEntity.ok(postResponses);
    }

}
