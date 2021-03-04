package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import fr.uge.jee.web.service.reddIGM.models.VotePost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotePostRepository extends CrudRepository<VotePost, Long> {

}
