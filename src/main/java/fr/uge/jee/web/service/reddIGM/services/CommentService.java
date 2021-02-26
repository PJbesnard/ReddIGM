package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.CommentDto;
import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

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
        if (comment.getSuperCommentId() != null)
            superComment = commentRepository.findById(comment.getSuperCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + comment.getSuperCommentId().toString() + " not found"));
            Post superCommentPost = superComment.getPost();
            if(superCommentPost == null) throw new NoSuchElementException("Post of comment " + superComment.getId() + " not found");
            if(!post.getPostId().equals(superCommentPost.getPostId())) throw new InvalidParameterException("super comment " + superComment.getId() + " is not a comment of post " + post.getPostId());
        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user, superComment);
        return CommentMapper.INSTANCE.toDto(repository.save(newComment), 0);
    }

    public List<CommentDto> getSubComments(Long commentId, OrderType orderType) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment " + commentId.toString() + " not found"));
        List<CommentDto> res = computeVote(repository.findAllBySuperComment(comment));
        return sortComments(res, orderType);
    }

    public List<CommentDto> getSubComments(Long commentId) {
        return getSubComments(commentId, OrderType.NEWEST);
    }


        private List<CommentDto> sortComments(List<CommentDto> commentsDtos, OrderType orderType) {
        switch (orderType) {
            case ASCENDING:
                commentsDtos.sort((o1, o2) -> {
                    if (o1.getNbVote() < o2.getNbVote()) return 1;
                    return 0;
                });
                break;
            case DESCENDING:
                commentsDtos.sort((o1, o2) -> {
                    if (o1.getNbVote() > o2.getNbVote()) return 1;
                    return 0;
                });
                break;
            default:
                commentsDtos.sort((o1, o2) -> {
                    if (o1.getCreationDate().isBefore(o2.getCreationDate())) return 1;
                    return 0;
                });
                break;
        }
        return commentsDtos;
    }

    public List<CommentDto> getAllCommentsForPost(Long postId, OrderType orderType) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post " + postId.toString() + " not found"));
        List<Comment> commentsForPost = repository.findByPost(post);
        List<Comment> onlySuperComments = new ArrayList<>();
        commentsForPost.forEach(com -> {if(com.getSuperComment() == null) onlySuperComments.add(com);});
        List<CommentDto> commentDtos = computeVote(onlySuperComments);
        return sortComments(commentDtos, orderType);
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        return  getAllCommentsForPost(postId, OrderType.NEWEST);
    }

    private List<CommentDto> computeVote(List<Comment> comments){
        List<CommentDto> res = new ArrayList<>();
        comments.forEach(c -> {
            int voteNb = 0;
            List<VoteComment> votes = voteCommentRepository.findAllByComment(c);
            for (VoteComment vote : votes) {
                if (vote.getType().equals(VoteType.DOWNVOTE)) voteNb--;
                else voteNb++;
            }
            res.add(CommentMapper.INSTANCE.toDto(c, voteNb));
        });
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
        if (voteByCommentAndUser.isPresent() && voteByCommentAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this comment");
        }
        voteByCommentAndUser.ifPresent(voteComment -> voteCommentRepository.deleteById(voteComment.getId()));
        voteCommentRepository.save(new VoteComment(vote.getVote(), user, comment));
        return CommentMapper.INSTANCE.toDto(comment, voteCommentRepository.findAllByComment(comment).size());
    }
}
