package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Type type;

    // TODO : Associer également le User au vote

    public Vote() {
    }

    public Vote(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return type == vote.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
