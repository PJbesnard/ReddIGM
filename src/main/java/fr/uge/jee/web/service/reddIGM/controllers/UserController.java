package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VotePostDto;
import fr.uge.jee.web.service.reddIGM.mapper.VoteCommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.VotePostMapper;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import fr.uge.jee.web.service.reddIGM.services.VoteCommentService;
import fr.uge.jee.web.service.reddIGM.services.VotePostService;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VotePostService votePostService;

    @Autowired
    private VoteCommentService voteCommentService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.getById(id));
    }

    @GetMapping("/usernames/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.of(userService.getByUsername(username));
    }

    @GetMapping("/emails/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.of(userService.getByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = {"/{id}/votes/posts", "/{id}/votes/posts/{orderType}"})
    public ResponseEntity<List<VotePostDto>> getPostVotes(@PathVariable Long id, @PathVariable(required = false) OrderType orderType) {
        System.out.println(orderType);
        return ResponseEntity.ok(
                votePostService.getAllByUserIdOrdered(id, orderType == null ? OrderType.DESCENDING : orderType)
                        // Converting VotePost to VotePostDto
                        .stream().map(VotePostMapper.INSTANCE::toDto).collect(Collectors.toList())
        );
    }

    @GetMapping(value = {"/{id}/votes/comments", "/{id}/votes/comments/{orderType}"})
    public ResponseEntity<List<VoteCommentDto>> getCommentVotes(@PathVariable Long id, @PathVariable(required = false) OrderType orderType) {
        return ResponseEntity.ok(
                voteCommentService.getAllByUserIdOrdered(id, orderType == null ? OrderType.DESCENDING : orderType)
                        // Converting VoteComment to VoteCommentDto
                        .stream().map(VoteCommentMapper.INSTANCE::toDto).collect(Collectors.toList())
        );
    }
}
