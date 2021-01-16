package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;


    public Post save(Post post){
        post.setCreationDate(LocalDateTime.now());
        return postRepository.save(post);

    }


    public Post getPost(Long id) {
        //TODO
        return null;
    }

    public List<Post> getPostsBySubject(Long id) {
        //TODO
        return  null;
    }

    public List<Post> getPostsByUsername(String username) {
        //TODO
        return null;
    }
}
