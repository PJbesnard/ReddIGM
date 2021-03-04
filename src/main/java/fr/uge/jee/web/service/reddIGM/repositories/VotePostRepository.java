package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotePostRepository extends CrudRepository<VotePost, Long> {
    Optional<VotePost> findByPostAndUser(Post post, User user);
    List<VotePost> findAllByPost(Post post);

}
