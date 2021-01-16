package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
//    List<Post> findAllPostBySubject(Subject subject);
//
//    List<Post> findAllPostByUser(User user);
}
