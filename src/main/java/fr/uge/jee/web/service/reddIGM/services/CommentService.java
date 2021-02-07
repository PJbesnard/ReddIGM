package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Comment save(Comment comment) {
        comment.setCreationDate(LocalDateTime.now());
        return repository.save(comment);
    }

    public List<Comment> getAllCommentsForPost(Long postId) {
        //TODO
        return null;
    }

    public List<Comment> getAllCommentsForUser(String userName) {
        //TODO
        return null;
    }
}
