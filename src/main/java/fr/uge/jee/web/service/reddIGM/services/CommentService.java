package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.UserMapper;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteCommentRepository voteCommentRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public Optional<CommentResponseDto> getById(long id) {
        return repository.findById(id).map(comment -> createResponseDto(comment, true, true, true));
    }

    public CommentResponseDto save(CommentRequestDto comment, User user) {
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(
                () -> new NoSuchElementException("Post " + comment.getPostId().toString() + " not found")
        );

        Comment superComment = null;

        if (comment.getSuperCommentId() != null) {
            superComment = repository.findById(comment.getSuperCommentId()).orElseThrow(
                    () -> new NoSuchElementException("Comment " + comment.getSuperCommentId().toString() + " not found")
            );
            Post superCommentPost = superComment.getPost();
            if (superCommentPost == null)
                throw new NoSuchElementException("Post of comment " + superComment.getId() + " not found");
            if (!post.getPostId().equals(superCommentPost.getPostId()))
                throw new InvalidParameterException("super comment " + superComment.getId() + " is not a comment of post " + post.getPostId());
        }

        LocalDateTime creationDate = LocalDateTime.now();
        Comment newComment = new Comment(comment.getText(), creationDate, post, user, superComment);

        return createResponseDto(repository.save(newComment), false, false, false);
    }

    private VoteType getVoteForCommentAndUser(long commentId, long userId) {
        Optional<VoteComment> myVote = voteCommentRepository.findByIdAndUserId(commentId, userId);

        if (myVote.isPresent()) {
            return myVote.get().getType();
        }

        return VoteType.NOVOTE;
    }

    public void deleteComment(Long id, User user) {
        Comment comment = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Comment " + id.toString() + " not found")
        );

        Authority userAuth = new Authority("USER");
        if (user.getAuthorities().contains(userAuth) && comment.getUser().getId() != user.getId()) {
            throw new InvalidParameterException("Comment " + id.toString() + " is not a comment of " + user.getUsername());
        }

        /* Removing sub comments and their associated votes (recursively) */
        repository.findAllBySuperComment(comment).forEach(c -> deleteComment(c.getId(), user));

        /* Removing the comment and its associated votes */
        repository.deleteById(id);
        voteCommentRepository.deleteAllByComment_Id(comment.getId());
    }

    public CommentResponseDto vote(VoteCommentDto vote, User user) {
        Comment comment = repository.findById(vote.getCommentId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getCommentId().toString() + " not found"));
        Optional<VoteComment> existingVote = voteCommentRepository.findByIdAndUserId(vote.getCommentId(), user.getId());

        if (existingVote.isPresent() && existingVote.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("User " + user.getUsername() + " has already voted " + vote.getVote() + " for this comment");
        }

        existingVote.ifPresent(voteComment -> voteCommentRepository.deleteById(voteComment.getId()));
        voteCommentRepository.save(new VoteComment(vote.getVote(), user, comment, LocalDateTime.now()));

        return createResponseDto(comment, true, true, true);
    }

    public List<CommentResponseDto> getCommentsByParentSortedByDate(long parentId, OrderType orderType) {
        List<Comment> queryResult = orderType == OrderType.ASCENDING ?
                repository.findAllByPostPostIdOrSuperCommentIdOrderByCreationDateAsc(parentId, parentId) :
                repository.findAllByPostPostIdOrSuperCommentIdOrderByCreationDateDesc(parentId, parentId);

        return queryResult.stream()
                .map(comment -> createResponseDto(comment, true, true, true))
                .collect(Collectors.toList());
    }

    public List<CommentResponseDto> getCommentsByParentSortedByScore(long parentId, OrderType sortOrder) {
        List<Object> queryResult = sortOrder == OrderType.ASCENDING ?
                repository.getScoresSortedAsc(parentId) :
                repository.getScoresSortedDesc(parentId);

        return queryResult.stream()
                .map(obj -> {
                    var array = (Object[]) obj;

                    return createResponseDto(((BigInteger) array[0]).longValue(),
                            (String) array[2],
                            ((Timestamp) array[1]).toLocalDateTime(),
                            ((BigInteger) array[3]).longValue(),
                            Objects.isNull(array[4]) ? null : ((BigInteger) array[4]).longValue(),
                            ((BigInteger) array[5]).longValue(),
                            true, true, true, true);
                })
                .collect(Collectors.toList());
    }

    public List<CommentResponseDto> getCommentsByUserSortedByDate(long userId, OrderType sortOrder) {
        List<Comment> queryResult = sortOrder == OrderType.ASCENDING ?
                repository.findAllByUserIdOrderByCreationDateAsc(userId) :
                repository.findAllByUserIdOrderByCreationDateDesc(userId);

        return queryResult.stream()
                .map(comment -> createResponseDto(comment, true, true, true))
                .collect(Collectors.toList());
    }

    private CommentResponseDto createResponseDto(Comment comment, boolean loadScore, boolean loadMyVote, boolean loadNbSubComments) {
        VoteType myVote = VoteType.NOVOTE;
        if (loadMyVote && authenticationService.isAuthenticated()) {
            myVote = getVoteForCommentAndUser(comment.getId(), authenticationService.getCurrentUser().getId());
        }

        return CommentMapper.INSTANCE.toDto(comment,
                loadNbSubComments ? (int) repository.countBySuperCommentId(comment.getId()) : 0,
                myVote,
                loadScore ? loadScore(comment.getId()) : 0,
                UserMapper.INSTANCE.toDto(comment.getUser()));
    }

    private CommentResponseDto createResponseDto(long commentId, String text, LocalDateTime timestamp, long postId,
                                                 Long superCommentId, long userId , boolean loadScore,
                                                 boolean loadMyVote, boolean loadNbSubComments, boolean loadUser) {

        VoteType myVote = VoteType.NOVOTE;
        if (loadMyVote && authenticationService.isAuthenticated()) {
            myVote = getVoteForCommentAndUser(commentId, authenticationService.getCurrentUser().getId());
        }

        User user = loadUser ? userRepository.findById(userId).orElse(null) : null;

        return new CommentResponseDto(
                commentId, text, timestamp, postId, superCommentId,
                loadScore ? loadScore(commentId) : 0,
                myVote,
                loadNbSubComments ? (int) repository.countBySuperCommentId(commentId) : 0,
                !Objects.isNull(user) ? UserMapper.INSTANCE.toDto(user) : null
        );
    }

    private int loadScore(long commentId) {
        int score = 0;

        var tmp = repository.getCommentScore(commentId);
        if (!Objects.isNull(tmp)) {
            score = tmp.intValue();
        }

        return score;
    }
}
