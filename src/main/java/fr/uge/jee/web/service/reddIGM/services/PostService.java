package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.PostMapper;
import fr.uge.jee.web.service.reddIGM.mapper.SubjectMapper;
import fr.uge.jee.web.service.reddIGM.mapper.UserMapper;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.*;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
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


    public void save(PostRequest postRequest, Long userId){
        Subject subreddit = subjectRepository.findById(postRequest.getSubjectId())
                .orElseThrow(() ->  new NoSuchElementException("Subject " + postRequest.getSubjectId() + " not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));
        postRepository.save(PostMapper.INSTANCE.map(postRequest, subreddit, user));

    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id.toString() + " not found"));
        int voteNb = calcScore(votePostRepository.findAllByPost(post));
        return PostMapper.INSTANCE.mapToDto(post, voteNb, VoteType.NOVOTE, SubjectMapper.INSTANCE.toDto(post.getSubject()), UserMapper.INSTANCE.toDto(post.getUser()), nbCommentsInPost(post));
    }

    public PostResponse getPost(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id.toString() + " not found"));
        int voteNb = calcScore(votePostRepository.findAllByPost(post));
        return PostMapper.INSTANCE.mapToDto(post, voteNb, getVoteForPostAndUser(post, user), SubjectMapper.INSTANCE.toDto(post.getSubject()), UserMapper.INSTANCE.toDto(post.getUser()), nbCommentsInPost(post));
    }

    public void deletePost(Long id, User user) {
        Authority adminAuth = new Authority("ADMIN");
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post " + id.toString() + " not found"));
        User principal = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("User " + user.getId() + " not found"));
        if(!principal.getAuthorities().contains(adminAuth)) throw new InvalidParameterException("Only admins can delete posts");
        List<Comment> comments = commentRepository.findByPost(post);
        comments.forEach(c ->{ if(c.getSuperComment() == null) commentService.deleteComment(c.getId(), principal);});
        votePostRepository.deleteAllByPost(post);
        postRepository.deleteById(id);
    }


    public List<PostResponse> getAllPosts(OrderType orderType) {
        List<PostResponse> postResponses = computeVote(postRepository.findAll(), null);
        return sortPosts(postResponses, orderType);

    }

    public List<PostResponse> getAllPosts(User user, OrderType orderType) {
        List<PostResponse> postResponses = computeVote(postRepository.findAll(), user);
        return sortPosts(postResponses, orderType);

    }

    private Integer nbCommentsInPost(Post post){
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().filter(c -> c.getSuperComment() == null).collect(Collectors.toList()).size();
    }

    public List<PostResponse> getPostsBySubjectId(Long subjectId) {
        Subject subreddit = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NoSuchElementException("Subject " + subjectId.toString() + " not found"));
        List<Post> posts = postRepository.findAllPostBySubject(subreddit);
        return computeVote(posts, null);

    }

    public List<PostResponse> getPostsById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Username " + userId.toString() + " not found"));
        return computeVote(postRepository.findByUser(user), null);
    }


    private int calcScore(List<VotePost> votes) {
        int voteNb = 0;
        for (VotePost vote : votes) {
            if (vote.getType().equals(VoteType.DOWNVOTE)) voteNb--;
            else voteNb++;
        }
        return voteNb;
    }

    private List<PostResponse> computeVote(List<Post> posts, User user){
        List<PostResponse> res = new ArrayList<>();
        posts.forEach(p -> {
            int voteNb = calcScore(votePostRepository.findAllByPost(p));
            if (user == null)res.add(PostMapper.INSTANCE.mapToDto(p, voteNb, VoteType.NOVOTE, SubjectMapper.INSTANCE.toDto(p.getSubject()), UserMapper.INSTANCE.toDto(p.getUser()), nbCommentsInPost(p)));
            else  res.add(PostMapper.INSTANCE.mapToDto(p, voteNb, getVoteForPostAndUser(p, user), SubjectMapper.INSTANCE.toDto(p.getSubject()), UserMapper.INSTANCE.toDto(p.getUser()), nbCommentsInPost(p)));
        });
        return res;
    }



    private List<PostResponse> sortPosts(List<PostResponse> postsDtos, OrderType orderType) {
        switch (orderType) {
            case ASCENDING:
                postsDtos.sort(Comparator.comparingInt(PostResponse::getVoteCount));
                break;
            case DESCENDING:
                postsDtos.sort((o1, o2) -> o2.getVoteCount() - o1.getVoteCount());
                break;
            default:
                postsDtos.sort((o1, o2) -> {
                    if (o1.getDuration().isBefore(o2.getDuration())) {
                        return 1;
                    }
                    else if(o1.getDuration().isEqual(o2.getDuration())) {
                        return 0;
                    }
                    return -1;
                });
                break;
        }
        return postsDtos;
    }

    private VoteType getVoteForPostAndUser(Post post,User user) {
        Optional<VotePost> myVote = votePostRepository.findByPostAndUser(post, user);
        VoteType v = VoteType.NOVOTE;
        if(myVote.isPresent()) v = myVote.get().getType();
        return v;
    }

    public PostResponse vote(VotePostDto vote, User user) {
        Post post = postRepository.findById(vote.getPostId()).orElseThrow(() -> new NoSuchElementException("Post " + vote.getPostId().toString() + " not found"));
        Optional<VotePost> voteByPostAndUser = votePostRepository.findByPostAndUser(post, user);
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this post");
        }
        voteByPostAndUser.ifPresent(votePost -> votePostRepository.deleteById(votePost.getId()));
        votePostRepository.save(new VotePost(vote.getVote(), user, post, LocalDateTime.now()));
        return PostMapper.INSTANCE.mapToDto(post, calcScore(votePostRepository.findAllByPost(post)), getVoteForPostAndUser(post, user), SubjectMapper.INSTANCE.toDto(post.getSubject()), UserMapper.INSTANCE.toDto(post.getUser()), nbCommentsInPost(post));
    }

    public List<PostResponse> getAllPostsForSubject(Long subjectId, OrderType orderType, User user) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new NoSuchElementException("Subject " + subjectId.toString() + " not found"));
        List<Post> postsForSubject = postRepository.findAllPostBySubject(subject);
        List<PostResponse> PostResponseDtos = computeVote(postsForSubject, user);
        return sortPosts(PostResponseDtos, orderType);
    }

    public List<PostResponse> getAllPostsForSubject(Long subjectId, OrderType orderType) {
        return  getAllPostsForSubject(subjectId, orderType, null);
    }

    /**
     * Return the list of posts id associated to their score (sum(upvote) - sum(downvote))
     * sorted by their score descending.
     *
     * @param subjectId The subject id containing the posts
     * @return The list of posts id
     */
    public List<ScoreDto> getScores(long subjectId) {
        return postRepository.getScores(subjectId).stream()
                .map(obj -> {
                    var array = (Object[]) obj;
                    return new ScoreDto(((BigInteger) array[0]).longValue(), ((BigInteger) array[1]).intValue());
                })
                .collect(Collectors.toList());
    }
}
