package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Comment save(CommentDto comment) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + comment.getPostId().toString() + " not found"));
        User user = userRepository.findByUsername(comment.getUserName()).orElseThrow(() -> new NoSuchElementException("User " + comment.getUserName() + " not found"));
        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user);
        return repository.save(newComment);
    }

    public List<Comment> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post " + postId.toString() + " not found"));
        return repository.findByPost(post);
    }

    public List<Comment> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("User " + userName + " not found"));
        return repository.findAllByUser(user);
    }
}
