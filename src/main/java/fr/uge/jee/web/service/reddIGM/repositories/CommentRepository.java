package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.Comment;
import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    long countByPostPostId(long postId);

    List<Comment> findAllByUser(User user);

    List<Comment> findAllBySuperComment(Comment comment);

    long countBySuperCommentId(long superCommentId);

    List<Comment> findAllByUserIdOrderByCreationDateAsc(long userId);

    List<Comment> findAllByUserIdOrderByCreationDateDesc(long userId);

    List<Comment> findAllByPostPostIdOrSuperCommentIdOrderByCreationDateDesc(long postId, long commentId);

    List<Comment> findAllByPostPostIdOrSuperCommentIdOrderByCreationDateAsc(long postId, long commentId);

    @Query(value = "SELECT comments.id, comments.creation_date, comments.text, comments.post_id," +
                           "comments.super_comment_id, comments.user_id,  SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "comment_votes RIGHT JOIN comments " +
                    "ON " +
                        "comment_id = comments.id " +
                    "WHERE " +
                        "(comments.post_id = :parentId AND comments.super_comment_id IS NULL OR " +
                        "comments.super_comment_id = :parentId) " +
                    "GROUP BY " +
                        "comments.id " +
                    "ORDER BY " +
                        "score ASC",
            nativeQuery = true)
    List<Object> getScoresSortedAsc(@Param("parentId") long parentId);

    @Query(value = "SELECT comments.id, comments.creation_date, comments.text, comments.post_id," +
                           "comments.super_comment_id, comments.user_id,  SUM(CASE " +
                            "WHEN type = 'UPVOTE' THEN 1 " +
                            "WHEN type = 'DOWNVOTE' THEN -1 " +
                            "ELSE 0 " +
                        "END) AS score " +
                    "FROM " +
                        "comment_votes RIGHT JOIN comments " +
                    "ON " +
                        "comment_id = comments.id " +
                    "WHERE " +
                        "(comments.post_id = :parentId AND comments.super_comment_id IS NULL OR " +
                        "comments.super_comment_id = :parentId) " +
                    "GROUP BY " +
                        "comments.id " +
                    "ORDER BY " +
                        "score DESC",
            nativeQuery = true)
    List<Object> getScoresSortedDesc(@Param("parentId") long parentId);

    @Query(value = "SELECT SUM(CASE " +
                                "WHEN type = 'UPVOTE' THEN 1 " +
                                "WHEN type = 'DOWNVOTE' THEN -1 " +
                                "ELSE 0 " +
                            "END) AS score " +
                    "FROM " +
                        "comment_votes " +
                    "WHERE " +
                        "comment_id = :commentId ",
            nativeQuery = true)
    Long getCommentScore(@Param("commentId") long commentId);
}
