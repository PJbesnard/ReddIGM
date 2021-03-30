package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findAllPostBySubject(Subject subject);

    List<Post> findAllByUser(User user);

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                           "posts.sub_id, posts.user_id, SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes RIGHT JOIN posts " +
                    "ON " +
                        "post_votes.post_id = posts.post_id " +
                    "GROUP BY " +
                        "posts.post_id," +
                        "posts.created_date " +
                    "ORDER BY " +
                        "score ASC, " +
                        "posts.created_date DESC",
            nativeQuery = true)
    List<Object> getScoresSortedAsc(Pageable pageable);

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                            "posts.sub_id, posts.user_id,"+
                    "SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes RIGHT JOIN posts " +
                    "ON " +
                        "post_votes.post_id = posts.post_id WHERE posts.sub_id = :subId " +
                    "GROUP BY " +
                        "posts.post_id," +
                        "posts.created_date " +
                    "ORDER BY " +
                        "score ASC, " +
                        "posts.created_date DESC",
            nativeQuery = true)
    List<Object> getScoresSortedAsc(@Param("subId") Long subId, Pageable pageable);

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                           "posts.sub_id, posts.user_id, SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes RIGHT JOIN posts " +
                    "ON " +
                        "post_votes.post_id = posts.post_id " +
                    "GROUP BY " +
                        "posts.post_id," +
                        "posts.created_date " +
                    "ORDER BY " +
                        "score DESC, " +
                        "posts.created_date DESC",
            nativeQuery = true)
    List<Object> getScoresSortedDesc(Pageable pageable);

    @Query(value = "SELECT posts.post_id, posts.created_date, posts.description, posts.post_name, posts.url, " +
                        "posts.sub_id, posts.user_id, SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "post_votes RIGHT JOIN posts " +
                    "ON " +
                        "post_votes.post_id = posts.post_id WHERE posts.sub_id = :subId " +
                    "GROUP BY " +
                        "posts.post_id," +
                        "posts.created_date " +
                    "ORDER BY " +
                        "score DESC, " +
                        "posts.created_date DESC",
                    nativeQuery = true)
    List<Object> getScoresSortedDesc(@Param("subId") Long subId, Pageable pageable);

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

    List<Post> findAllByOrderByCreatedDateAsc();

    List<Post> findAllByOrderByCreatedDateDesc();

    List<Post> findAll(Pageable pageable);

    List<Post> findAllBySubjectId(long subjectId, Pageable pageable);

    List<Post> findAllByUserIdOrderByCreatedDateAsc(long subjectId);

    List<Post> findAllByUserIdOrderByCreatedDateDesc(long subjectId);
}
