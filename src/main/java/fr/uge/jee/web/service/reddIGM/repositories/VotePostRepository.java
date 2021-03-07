package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.models.VotePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotePostRepository extends JpaRepository<VotePost, Long> {
    Optional<VotePost> findByPostAndUser(Post post, User user);
    List<VotePost> findAllByPost(Post post);
    List<VotePost> findAllByUserId(long userId);
    List<VotePost> findAllByUserIdOrderByCreationDateAsc(long userId);
    List<VotePost> findAllByUserIdOrderByCreationDateDesc(long userId);
}
