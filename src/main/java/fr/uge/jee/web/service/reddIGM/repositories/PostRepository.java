package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllPostBySubject(Subject subject);

    List<Post> findAllPostByUser(User user);
    List<Post> findByUser(User user);

    @Query(value = "SELECT post_votes.post_id, SUM(CASE\n" +
            "\t\t\t\t\t\t\t\tWHEN type = 'UPVOTE' THEN 1\n" +
            "\t\t\t\t\t\t\t\tWHEN type = 'DOWNVOTE' THEN -1\n" +
            "\t\t\t\t\t\t\t\tELSE 0\n" +
            "\t\t\t\t\t\t\t   END) AS score\n" +
            "FROM\n" +
            "\tpost_votes, posts\n" +
            "WHERE\n" +
            "\tpost_votes.post_id = posts.post_id AND\n" +
            "\tposts.sub_id = :subjectId\n" +
            "GROUP BY\n" +
            "\tpost_votes.post_id\n" +
            "ORDER BY\n" +
            "\tscore DESC", nativeQuery = true)
    List<Object> getScores(@Param("subjectId") long subjectId);
}
