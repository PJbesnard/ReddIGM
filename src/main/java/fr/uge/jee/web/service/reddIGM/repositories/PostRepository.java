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

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                           "posts.sub_id, posts.user_id, SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes, posts " +
                    "WHERE " +
                        "post_votes.post_id = posts.post_id AND " +
                        "posts.sub_id = :subjectId " +
                    "GROUP BY " +
                        "posts.post_id " +
                    "ORDER BY " +
                        "score ASC",
            nativeQuery = true)
    List<Object> getScoresSortedAsc(@Param("subjectId") long subjectId);

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                           "posts.sub_id, posts.user_id, SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes, posts " +
                    "WHERE " +
                        "post_votes.post_id = posts.post_id AND " +
                        "posts.sub_id = :subjectId " +
                    "GROUP BY " +
                        "posts.post_id " +
                    "ORDER BY " +
                        "score ASC",
            nativeQuery = true)
    List<Object> getScoresSortedDesc(@Param("subjectId") long subjectId);

    @Query(value = "SELECT SUM(CASE " +
                                    "WHEN type = 'UPVOTE' THEN 1 " +
                                    "WHEN type = 'DOWNVOTE' THEN -1 " +
                                    "ELSE 0 " +
                                "END) AS score " +
                    "FROM " +
                        "post_votes " +
                    "WHERE " +
                        "post_id = :postId ",
            nativeQuery = true)
    Long getPostScore(@Param("postId") long postId);

    List<Post> findAllByUserIdOrderByCreatedDateAsc(@Param("subjectId") long subjectId);

    List<Post> findAllByUserIdOrderByCreatedDateDesc(@Param("subjectId") long subjectId);
}
