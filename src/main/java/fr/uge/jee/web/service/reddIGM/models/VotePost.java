package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User owner;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "commentId", referencedColumnName = "postId")
    private Post post;

    public VotePost() {
    }

    public VotePost(@NotNull VoteType type, @NotNull User owner, @NotNull Post post) {
        this.type = type;
        this.owner = owner;
        this.post = post;
    }

    public VoteType getType() {
        return type;
    }

    public void setType(VoteType type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "VotePost{" +
                "id=" + id +
                ", type=" + type +
                ", owner=" + owner +
                ", post=" + post +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotePost votePost = (VotePost) o;
        return id == votePost.id &&
                type == votePost.type &&
                owner.equals(votePost.owner) &&
                post.equals(votePost.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, owner, post);
    }
}
