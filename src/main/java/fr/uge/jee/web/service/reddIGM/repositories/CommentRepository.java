package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);

    List<Comment> findAllBySuperComment(Comment comment);

    @Query(value = "SELECT comment_id, SUM(CASE\n" +
            "\t\t\t\t\t   \tWHEN type = 'UPVOTE' THEN 1\n" +
            "\t\t\t\t\t   \tWHEN type = 'DOWNVOTE' THEN -1\n" +
            "\t\t\t\t\t   \tELSE 0\n" +
            "\t\t\t\t\t   END) AS score\n" +
            "\t\t\t\t\t\t\n" +
            "FROM\n" +
            "\tcomment_votes, comments\n" +
            "WHERE\n" +
            "\tcomment_id = comments.id AND\n" +
            "\t(comments.post_id = :parentId AND comments.super_comment_id IS NULL OR\n" +
            "\tcomments.super_comment_id = :parentId)\n" +
            "GROUP BY\n" +
            "\tcomment_id\n" +
            "ORDER BY\n" +
            "\tscore DESC", nativeQuery = true)
    List<Object> getScores(@Param("parentId") long parentId);
}
