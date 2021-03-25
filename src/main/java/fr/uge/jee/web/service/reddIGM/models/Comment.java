package fr.uge.jee.web.service.reddIGM.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;
    @NotNull
    private LocalDateTime creationDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Nullable
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "superCommentId", referencedColumnName = "id")
    private Comment superComment;

    public Comment() {
    }

    public Comment(String text, LocalDateTime creationDate, Post post, User user, Comment superComment) {
        this.text = text;
        this.creationDate = creationDate;
        this.post = post;
        this.user = user;
        this.superComment = superComment;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getSuperComment() {
        return superComment;
    }

    public void setSuperComment(Comment superComment) {
        this.superComment = superComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", post=" + post +
                ", user=" + user +
                ", superComment=" + superComment +
                '}';
    }
}
