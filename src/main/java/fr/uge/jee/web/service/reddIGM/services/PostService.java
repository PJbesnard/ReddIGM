package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.PostRequest;
import fr.uge.jee.web.service.reddIGM.dto.PostResponse;
import fr.uge.jee.web.service.reddIGM.mapper.PostMapper;
import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import fr.uge.jee.web.service.reddIGM.repositories.SubjectRepository;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    private PostRepository postRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private AuthenticationService authService;
    private PostMapper postMapper;


    public void save(PostRequest postRequest){
        Subject subreddit = subjectRepository.findByName(postRequest.getSubjectName())
                .orElseThrow(() ->  new NoSuchElementException("PostRequest " + postRequest.getPostId().toString() + " not found"));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));

    }


    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id " + id.toString() + " not found"));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubject(Long subredditId) {
        Subject subreddit = subjectRepository.findById(subredditId)
                .orElseThrow(() -> new NoSuchElementException("Subject " + subredditId.toString() + " not found"));
        List<Post> posts = postRepository.findAllPostBySubject(subreddit.getName());
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Username " + username + " not found"));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
