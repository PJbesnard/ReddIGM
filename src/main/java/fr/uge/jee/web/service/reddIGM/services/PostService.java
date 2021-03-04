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
        return computeVote(postRepository.findAll(), null);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubjectId(Long subjectId) {
        Subject subreddit = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NoSuchElementException("Subject " + subjectId.toString() + " not found"));
        List<Post> posts = postRepository.findAllPostBySubject(subreddit);
        return computeVote(posts, null);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsById(Long userId) {
        System.out.println("TESTTTTTT"+userRepository.findById(userId));
        System.out.println("TEST"+userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Username " + userId.toString() + " not found"));
        return computeVote(postRepository.findByUser(user), null);
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
            if (user == null) res.add(postMapper.mapToDto(p, voteNb, VoteType.NOVOTE));
            else  res.add(postMapper.mapToDto(p, voteNb, getVoteForPostAndUser(p, user)));
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
        System.out.println("TEST"+vote.getPostId());
        Post post = postRepository.findById(vote.getPostId()).orElseThrow(() -> new NoSuchElementException("Comment " + vote.getPostId().toString() + " not found"));
        Optional<VotePost> voteByPostAndUser = votePostRepository.findByPostAndUser(post, user);
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getType().equals(vote.getVote())) {
            throw new IllegalArgumentException("You have already voted " + vote.getVote() + " for this post");
        }
        voteByPostAndUser.ifPresent(votePost -> votePostRepository.deleteById(votePost.getId()));
        votePostRepository.save(new VotePost(vote.getVote(), user, post));
        return postMapper.mapToDto(post, votePostRepository.findAllByPost(post).size(), getVoteForPostAndUser(post, user));
    }








}
