package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "post_votes")
public class VotePost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType type;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    public VotePost() {
    }

    public VotePost(VoteType type, User user, Post post, LocalDateTime creationDate) {
        this.type = type;
        this.user = user;
        this.post = post;
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VoteType getType() {
        return type;
    }

    public void setType(VoteType type) {
        this.type = type;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getOwner() {
        return user;
    }

    public void setOwner(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotePost votePost = (VotePost) o;
        return id == votePost.id &&
                type == votePost.type &&
                creationDate.equals(votePost.creationDate) &&
                user.equals(votePost.user) &&
                post.equals(votePost.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, creationDate, user, post);
    }

    @Override
    public String toString() {
        return "VotePost{" +
                "id=" + id +
                ", type=" + type +
                ", creationDate=" + creationDate +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
