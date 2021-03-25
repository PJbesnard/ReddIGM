package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.mapper.PostMapper;
import fr.uge.jee.web.service.reddIGM.mapper.SubjectMapper;
import fr.uge.jee.web.service.reddIGM.mapper.UserMapper;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.*;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VotePostRepository votePostRepository;

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthenticationService authenticationService;


    public void save(PostRequest postRequest, Long userId){
        Subject subreddit = subjectRepository.findById(postRequest.getSubjectId())
                .orElseThrow(() ->  new NoSuchElementException("Subject " + postRequest.getSubjectId() + " not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));
        postRepository.save(PostMapper.INSTANCE.map(postRequest, subreddit, user));
    }

    public Optional<PostResponse> getPost(Long id) {
        return postRepository.findById(id).map(post -> createPostResponseDto(post, true, true, true));
    }

    public void deletePost(Long id, User user) {
        Authority adminAuth = new Authority("ADMIN");
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post " + id.toString() + " not found"));
        User principal = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("User " + user.getId() + " not found"));

        if (!principal.getAuthorities().contains(adminAuth)) {
            throw new InvalidParameterException("Only admins can delete posts");
        }

        commentRepository.findByPost(post).forEach(c -> {
            if (c.getSuperComment() == null) {
                commentService.deleteComment(c.getId(), principal);
            }
        });

        votePostRepository.deleteAllByPost(post);
        postRepository.deleteById(id);
    }

    private VoteType getVoteForPostAndUser(long postId, long userId) {
        Optional<VotePost> myVote = votePostRepository.findByPostPostIdAndUserId(postId, userId);

        if (myVote.isPresent()) {
            return myVote.get().getType();
        }

        return VoteType.NOVOTE;
    }

    public PostResponse vote(VotePostDto vote, User user) {
        Post post = postRepository.findById(vote.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + vote.getPostId().toString() + " not found"));

        Optional<VotePost> voteByPostAndUser = votePostRepository.findByPostAndUser(post, user);
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this post");
        }

        voteByPostAndUser.ifPresent(votePost -> votePostRepository.deleteById(votePost.getId()));
        votePostRepository.save(new VotePost(vote.getVote(), user, post, LocalDateTime.now()));

        return createPostResponseDto(post, true, true, true);
    }

    public List<PostResponse> getAllPostsSortedByDate(OrderType sortType) {
        List<Post> queryResult = sortType == OrderType.ASCENDING ?
                postRepository.findAllByOrderByCreatedDateAsc() :
                postRepository.findAllByOrderByCreatedDateDesc();

        return queryResult.stream()
                .map(post ->
                        createPostResponseDto(post, true, true, true)
                ).collect(Collectors.toList());
    }

    public List<PostResponse> getPostsBySubjectSortedByScore(long subjectId, OrderType sortType) {
        List<Object> queryResult = sortType == OrderType.ASCENDING ?
                postRepository.getScoresSortedAsc() :
                postRepository.getScoresSortedDesc();

        return queryResult.stream()
                .filter(obj -> {
                    var array = (Object[]) obj;

                    return ((BigInteger) array[5]).longValue() == subjectId;
                })
                .map(obj -> {
                    var array = (Object[]) obj;

                    return createPostResponseDto(
                        ((BigInteger) array[0]).longValue(),
                        ((String) array[3]),
                        ((String) array[4]),
                        (String) array[2],
                        ((BigInteger) array[5]).longValue(),
                        ((BigInteger) array[6]).longValue(),
                        ((Timestamp) array[1]).toLocalDateTime(),
                        true, true, true, true, true);
                })
                .collect(Collectors.toList());
    }

    public List<PostResponse> getAllPostsSortedByScore(OrderType sortType) {
        List<Object> queryResult = sortType == OrderType.ASCENDING ?
                postRepository.getScoresSortedAsc() :
                postRepository.getScoresSortedDesc();

        return queryResult.stream()
                .map(obj -> {
                    var array = (Object[]) obj;

                    return createPostResponseDto(
                            ((BigInteger) array[0]).longValue(),
                            ((String) array[3]),
                            ((String) array[4]),
                            (String) array[2],
                            ((BigInteger) array[5]).longValue(),
                            ((BigInteger) array[6]).longValue(),
                            ((Timestamp) array[1]).toLocalDateTime(),
                            true, true, true, true, true);
                })
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByUserSortedByDate(long userId, OrderType sortType) {
        List<Post> queryResult = sortType == OrderType.ASCENDING ?
                postRepository.findAllByUserIdOrderByCreatedDateAsc(userId) :
                postRepository.findAllByUserIdOrderByCreatedDateDesc(userId);

        return queryResult.stream()
                .map(post ->
                        createPostResponseDto(post, true, true, true)
                ).collect(Collectors.toList());
    }

    public List<PostResponse> getPostsBySubjectSortedByDate(long subjectId, OrderType sortType) {
        List<Post> queryResult = sortType == OrderType.ASCENDING ?
                postRepository.findAllBySubjectIdOrderByCreatedDateAsc(subjectId) :
                postRepository.findAllBySubjectIdOrderByCreatedDateDesc(subjectId);

        return queryResult.stream()
                .map(post ->
                    createPostResponseDto(post, true, true, true)
                ).collect(Collectors.toList());
    }

    private PostResponse createPostResponseDto(Post post, boolean loadScore, boolean loadMyVote, boolean loadNbComments) {
        VoteType myVote = VoteType.NOVOTE;
        if (loadMyVote && authenticationService.isAuthenticated()) {
            myVote = getVoteForPostAndUser(post.getPostId(), authenticationService.getCurrentUser().getId());
        }

        return new PostResponse(
            post.getPostId(),
            post.getPostName(),
            post.getUrl(),
            post.getDescription(),
            SubjectMapper.INSTANCE.toDto(post.getSubject()),
            loadScore ? loadScore(post.getPostId()) : 0,
            post.getCreatedDate(),
            myVote,
            UserMapper.INSTANCE.toDto(post.getUser()),
            loadNbComments ? (int) commentRepository.countByPostPostId(post.getPostId()) : 0
        );
    }

    private PostResponse createPostResponseDto(long postId, String postName, String postUrl, String postDescription,
                                               long subjectId, long userId, LocalDateTime timestamp, boolean loadScore,
                                               boolean loadMyVote, boolean loadSubject, boolean loadUser, boolean loadNbComments) {
        VoteType myVote = VoteType.NOVOTE;
        if (loadMyVote && authenticationService.isAuthenticated()) {
            myVote = getVoteForPostAndUser(postId, authenticationService.getCurrentUser().getId());
        }

        return new PostResponse(
                postId,
                postName,
                postUrl,
                postDescription,
                loadSubject ? SubjectMapper.INSTANCE.toDto(subjectRepository.findById(subjectId).orElseGet(null)) : null,
                loadScore ? loadScore(postId) : 0,
                timestamp,
                myVote,
                loadUser ? UserMapper.INSTANCE.toDto(userRepository.findById(userId).orElseGet(null)) : null,
                loadNbComments ? (int) commentRepository.countByPostPostId(postId) : 0
        );
    }

    private int loadScore(long postId) {
        int score = 0;

        var tmp = postRepository.getPostScore(postId);
        if (!Objects.isNull(tmp)) {
            score = tmp.intValue();
        }

        return score;
    }
}
