package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.CommentRequestDto;
import fr.uge.jee.web.service.reddIGM.dto.CommentResponseDto;
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
import java.util.stream.Collectors;

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

    public CommentResponseDto save(CommentRequestDto comment, User user) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + comment.getPostId().toString() + " not found"));
        Comment superComment = null;
        if (comment.getSuperCommentId() != null) {
            superComment = commentRepository.findById(comment.getSuperCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + comment.getSuperCommentId().toString() + " not found"));
            Post superCommentPost = superComment.getPost();
            if (superCommentPost == null)
                throw new NoSuchElementException("Post of comment " + superComment.getId() + " not found");
            if (!post.getPostId().equals(superCommentPost.getPostId()))
                throw new InvalidParameterException("super comment " + superComment.getId() + " is not a comment of post " + post.getPostId());
        }
        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user, superComment);
        return CommentMapper.INSTANCE.toDto(repository.save(newComment), 0, null, 0);
    }

    public List<CommentResponseDto> getSubComments(Long commentId, OrderType orderType, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment " + commentId.toString() + " not found"));
        List<CommentResponseDto> res = computeVote(repository.findAllBySuperComment(comment), user);
        return sortComments(res, orderType);
    }

    public List<CommentResponseDto> getSubComments(Long commentId, OrderType orderType) {
        return getSubComments(commentId, orderType, null);
    }


    private List<CommentResponseDto> sortComments(List<CommentResponseDto> commentsDtos, OrderType orderType) {
        switch (orderType) {
            case ASCENDING:
                commentsDtos.sort(Comparator.comparingInt(CommentResponseDto::getNbVote));
                break;
            case DESCENDING:
                commentsDtos.sort((o1, o2) -> o2.getNbVote() - o1.getNbVote());
                break;
            default:
                commentsDtos.sort((o1, o2) -> {
                    if (o1.getCreationDate().isBefore(o2.getCreationDate())) {
                        return 1;
                    }
                    else if(o1.getCreationDate().isEqual(o2.getCreationDate())) {
                        return 0;
                    }
                    return -1;
                });
                break;
        }
        return commentsDtos;
    }

    public List<CommentResponseDto> getAllCommentsForPost(Long postId, OrderType orderType, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("Post " + postId.toString() + " not found"));
        List<Comment> commentsForPost = repository.findByPost(post);
        List<Comment> onlySuperComments = new ArrayList<>();
        commentsForPost.forEach(com -> {
            if (com.getSuperComment() == null) onlySuperComments.add(com);
        });
        List<CommentResponseDto> commentResponseDtos = computeVote(onlySuperComments, user);
        return sortComments(commentResponseDtos, orderType);
    }

    public List<CommentResponseDto> getAllCommentsForPost(Long postId, OrderType orderType) {
        return getAllCommentsForPost(postId, orderType, null);
    }

    private VoteType getVoteForCommentAndUser(Comment comment, User user) {
        Optional<VoteComment> myVote = voteCommentRepository.findByCommentAndUser(comment, user);
        VoteType v = null;

        if (myVote.isPresent()) {
            return myVote.get().getType();
        }
        return VoteType.NOVOTE;
    }

    private Integer nbCommentsInComment(Comment comment){
        List<Comment> comments = commentRepository.findAllBySuperComment(comment);
        return comments.stream().filter(c -> c.getSuperComment() != null).collect(Collectors.toList()).size();
    }

    private int calcScore(List<VoteComment> votes) {
        int voteNb = 0;
        for (VoteComment vote : votes) {
            if (vote.getType().equals(VoteType.DOWNVOTE)) voteNb--;
            else voteNb++;
        }
        return voteNb;
    }

    private List<CommentResponseDto> computeVote(List<Comment> comments, User user) {
        List<CommentResponseDto> res = new ArrayList<>();
        comments.forEach(c -> {
            int voteNb = calcScore(voteCommentRepository.findAllByComment(c));
            if (user == null) {
                res.add(CommentMapper.INSTANCE.toDto(c, voteNb, VoteType.NOVOTE, nbCommentsInComment(c)));
            } else {
                res.add(CommentMapper.INSTANCE.toDto(c, voteNb, getVoteForCommentAndUser(c, user), nbCommentsInComment(c)));
            }
        });
        return res;
    }

    public List<CommentResponseDto> getAllCommentsForUser(String userName, User principal) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("User " + userName + " not found"));
        List<Comment> userComments = repository.findAllByUser(user);
        List<CommentResponseDto> res = computeVote(userComments, principal);
        return sortComments(res, OrderType.NEWEST);
    }

    public List<CommentResponseDto> getAllCommentsForUser(String userName) {
        return getAllCommentsForUser(userName, null);
    }

    public CommentResponseDto vote(VoteCommentDto vote, User user) {
        Comment comment = commentRepository.findById(vote.getCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getCommentId().toString() + " not found"));
        Optional<VoteComment> voteByCommentAndUser = voteCommentRepository.findByCommentAndUser(comment, user);
        if (voteByCommentAndUser.isPresent() && voteByCommentAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this comment");
        }
        voteByCommentAndUser.ifPresent(voteComment -> voteCommentRepository.deleteById(voteComment.getId()));
        voteCommentRepository.save(new VoteComment(vote.getVote(), user, comment, LocalDateTime.now()));
        return CommentMapper.INSTANCE.toDto(comment, calcScore(voteCommentRepository.findAllByComment(comment)), getVoteForCommentAndUser(comment, user), nbCommentsInComment(comment));
    }
}
