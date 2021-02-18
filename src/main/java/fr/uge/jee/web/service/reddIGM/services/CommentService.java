package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.VoteCommentMapper;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    public CommentDto save(CommentDto comment) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + comment.getPostId().toString() + " not found"));
        User user = userRepository.findByUsername(comment.getUserName()).orElseThrow(() -> new NoSuchElementException("User " + comment.getUserName() + " not found"));
        Comment superComment = null;
        if(comment.getSuperCommentId() != null) superComment = commentRepository.findById(comment.getSuperCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + comment.getSuperCommentId().toString() + " not found"));
        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user, superComment);
        return CommentMapper.INSTANCE.toDto(repository.save(newComment));
    }

    public List<CommentDto> getSubComments(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment " + commentId.toString() + " not found"));
        return repository.findAllBySuperComment(comment).stream().map(CommentMapper.INSTANCE::toDto).collect(toList());
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post " + postId.toString() + " not found"));
        var truc = repository.findByPost(post).stream();
        var machin = truc.map(CommentMapper.INSTANCE::toDto);
        return machin.collect(toList());
    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("User " + userName + " not found"));
        return repository.findAllByUser(user).stream().map(CommentMapper.INSTANCE::toDto).collect(toList());
    }

    public VoteCommentDto vote(VoteCommentDto vote) {
        Comment comment = commentRepository.findById(vote.getCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getCommentId().toString() + " not found"));
        User user = userRepository.findByUsername(vote.getUsername()).orElseThrow(() -> new NoSuchElementException("User " + vote.getUsername() + " not found"));
        Optional<VoteComment> voteByCommentAndUser = voteCommentRepository.findByCommentAndUser(comment, user);
        if(voteByCommentAndUser.isPresent() && voteByCommentAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this comment");
        }
        if(voteByCommentAndUser.isPresent()) {
            voteCommentRepository.deleteById(voteByCommentAndUser.get().getId());
        }
        return VoteCommentMapper.INSTANCE.toDto(voteCommentRepository.save(new VoteComment(vote.getVote(), user, comment)));
    }
}
