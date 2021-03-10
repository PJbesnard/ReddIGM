package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.UserDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VotePostDto;
import fr.uge.jee.web.service.reddIGM.mapper.UserMapper;
import fr.uge.jee.web.service.reddIGM.mapper.VoteCommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.VotePostMapper;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import fr.uge.jee.web.service.reddIGM.services.VoteCommentService;
import fr.uge.jee.web.service.reddIGM.services.VotePostService;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userService.getById(id);

        return user.map(value -> ResponseEntity.ok(UserMapper.INSTANCE.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usernames/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        var user = userService.getByUsername(username);

        return user.map(value -> ResponseEntity.ok(UserMapper.INSTANCE.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/emails/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        var user = userService.getByEmail(email);

        return user.map(value -> ResponseEntity.ok(UserMapper.INSTANCE.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll()
                .stream().map(UserMapper.INSTANCE::toDto).collect(Collectors.toList()));
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

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        var ctx = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User authUser = (User) ctx;

        if (authUser.getId() != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.getById(id).get();
        user.setPicture(userDto.getPicture());
        user.setDescription(userDto.getDescription());
        user.setNewsletterSubscriber(userDto.getNewsletterSubscriber());

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toDto(userService.save(user)));
    }

    @PostMapping("/editPassword/{id}")
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable Long id, @RequestBody String password) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authUser.getId() != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.getById(id).get();
        user.setPassword(passwordEncoder.encode(password));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toDto(userService.save(user)));
    }
}
