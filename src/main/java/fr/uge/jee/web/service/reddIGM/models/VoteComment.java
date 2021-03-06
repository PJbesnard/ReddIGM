package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "comment_votes")
public class VoteComment {

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
    @JoinColumn(name = "commentId", referencedColumnName = "id")
    private Comment comment;

    public VoteComment() {
    }

    public VoteComment(VoteType type, User user, Comment comment, LocalDateTime creationDate) {
        this.type = type;
        this.user = user;
        this.comment = comment;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteComment that = (VoteComment) o;
        return id == that.id &&
                type == that.type &&
                creationDate.equals(that.creationDate) &&
                user.equals(that.user) &&
                comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, creationDate, user, comment);
    }

    @Override
    public String toString() {
        return "VoteComment{" +
                "id=" + id +
                ", type=" + type +
                ", creationDate=" + creationDate +
                ", user=" + user +
                ", comment=" + comment +
                '}';
    }
}
