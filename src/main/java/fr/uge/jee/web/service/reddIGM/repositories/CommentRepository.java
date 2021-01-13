package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
