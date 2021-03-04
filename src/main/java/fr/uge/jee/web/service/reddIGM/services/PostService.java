package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.mapper.CommentMapper;
import fr.uge.jee.web.service.reddIGM.mapper.PostMapper;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.*;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    private AuthenticationService authService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private VotePostRepository votePostRepository;


    public void save(PostRequest postRequest, Long userId){
        Subject subreddit = subjectRepository.findById(postRequest.getSubjectId())
                .orElseThrow(() ->  new NoSuchElementException("Subject " + postRequest.getSubjectId() + " not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));
        postRepository.save(postMapper.map(postRequest, subreddit, user));

    }


    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id.toString() + " not found"));
        return postMapper.mapToDto(post, post.getVoteCount(), getVoteForPostAndUser(post, post.getUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<PostResponse> res = new ArrayList<>();
        List<PostResponse> computeVote = computeVote(postRepository.findAll(), null);
        return computeVote;

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubject(Long subredditId) {
        Subject subreddit = subjectRepository.findById(subredditId)
                .orElseThrow(() -> new NoSuchElementException("Subject " + subredditId.toString() + " not found"));
        List<Post> posts = postRepository.findAllPostBySubject(subreddit);
        return computeVote(posts, null);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Username " + userId.toString() + " not found"));
        List<PostResponse> computeVote = computeVote(postRepository.findByUser(user), null);
        return computeVote;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostsByUsername(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new NoSuchElementException("Username " + userName + " not found"));
        List<PostResponse> computeVote = computeVote(postRepository.findByUser(user), null);
        return computeVote;
    }

    private List<PostResponse> computeVote(List<Post> posts, User user){
        List<PostResponse> res = new ArrayList<>();
        posts.forEach(p -> {
            int voteNb = 0;
            List<VotePost> votes = votePostRepository.findAllByPost(p);
            for (VotePost vote : votes) {
                if (vote.getType().equals(VoteType.DOWNVOTE)) voteNb--;
                else voteNb++;
            }
            res.add(postMapper.mapToDto(p, voteNb, getVoteForPostAndUser(p, user)));
        });
        return res;
    }

    private VoteType getVoteForPostAndUser(Post post,User user) {
        Optional<VotePost> myVote = votePostRepository.findByPostAndUser(post, user);
        VoteType v = null;
        if(myVote.isPresent()) v = myVote.get().getType();
        return v;
    }

    public PostResponse vote(VotePostDto vote, User user) {
        Post post = postRepository.findById(vote.getPostId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getPostId().toString() + " not found"));
        Optional<VotePost> voteByPostAndUser = votePostRepository.findByPostAndUser(post, user);
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this comment");
        }
        voteByPostAndUser.ifPresent(votePost -> votePostRepository.deleteById(votePost.getId()));
        votePostRepository.save(new VotePost(vote.getVote(), user, post));
        return postMapper.mapToDto(post, votePostRepository.findAllByPost(post).size(), getVoteForPostAndUser(post, user));
    }


    private List<PostResponse> sortPosts(List<PostResponse> postsDtos, OrderType orderType) {
        switch (orderType) {
            case ASCENDING:
                postsDtos.sort((o1, o2) -> {
                    if (o1.getVoteCount() < o2.getVoteCount()) return 1;
                    return 0;
                });
                break;
            case DESCENDING:
                postsDtos.sort((o1, o2) -> {
                    if (o1.getVoteCount() > o2.getVoteCount()) return 1;
                    return 0;
                });
                break;
            default:
                postsDtos.sort((o1, o2) -> {
                    if (o1.getDuration().isBefore(o2.getDuration())) return 1;
                    return 0;
                });
                break;
        }
        return postsDtos;
    }





}
