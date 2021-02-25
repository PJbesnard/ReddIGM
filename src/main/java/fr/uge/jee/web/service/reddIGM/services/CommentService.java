package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteCommentRepository voteCommentRepository;

    public CommentDto save(CommentDto comment, User user) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + comment.getPostId().toString() + " not found"));
        Comment superComment = null;
        if(comment.getSuperCommentId() != null) superComment = commentRepository.findById(comment.getSuperCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + comment.getSuperCommentId().toString() + " not found"));
        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user, superComment);
        return CommentMapper.INSTANCE.toDto(repository.save(newComment), 0);
    }

    public List<CommentDto> getSubComments(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment " + commentId.toString() + " not found"));
        List<CommentDto> res = new ArrayList<>();
        repository.findAllBySuperComment(comment).forEach(c -> res.add(CommentMapper.INSTANCE.toDto(c, voteCommentRepository.findAllByComment(c).size())));
        return res;
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post " + postId.toString() + " not found"));
        List<CommentDto> res = new ArrayList<>();
        repository.findByPost(post).forEach(c -> res.add(CommentMapper.INSTANCE.toDto(c, voteCommentRepository.findAllByComment(c).size())));
        return res;
    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("User " + userName + " not found"));
        List<CommentDto> res = new ArrayList<>();
        repository.findAllByUser(user).forEach(c -> res.add(CommentMapper.INSTANCE.toDto(c, voteCommentRepository.findAllByComment(c).size())));
        return res;
    }

    public CommentDto vote(VoteCommentDto vote, User user) {
        Comment comment = commentRepository.findById(vote.getCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getCommentId().toString() + " not found"));
        Optional<VoteComment> voteByCommentAndUser = voteCommentRepository.findByCommentAndUser(comment, user);
        if(voteByCommentAndUser.isPresent() && voteByCommentAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this comment");
        }
        if(voteByCommentAndUser.isPresent()) {
            voteCommentRepository.deleteById(voteByCommentAndUser.get().getId());
        }
        voteCommentRepository.save(new VoteComment(vote.getVote(), user, comment));
        return CommentMapper.INSTANCE.toDto(comment, voteCommentRepository.findAllByComment(comment).size());
    }
}
