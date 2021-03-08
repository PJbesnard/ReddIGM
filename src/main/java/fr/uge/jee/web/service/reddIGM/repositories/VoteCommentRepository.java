package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteCommentRepository extends JpaRepository<VoteComment, Long> {
    Optional<VoteComment> findByCommentAndUser(Comment comment, User user);
    List<VoteComment> findAllByComment(Comment comment);
    List<VoteComment> findAllByUserId(long userId);
    List<VoteComment> findAllByUserIdOrderByCreationDateAsc(long userId);
    List<VoteComment> findAllByUserIdOrderByCreationDateDesc(long userId);

    void deleteAllByComment_Id(long comment_Id);
}
