package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "Votes")
public class Vote {

    public enum Type {
        UPVOTE, DOWNVOTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @ManyToOne
    private User owner;

    public Vote() {
    }

    public Vote(@NotNull Type type, @NotNull User owner) {
        this.type = type;
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", type=" + type +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id &&
                type == vote.type &&
                owner.equals(vote.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, owner);
    }
}
