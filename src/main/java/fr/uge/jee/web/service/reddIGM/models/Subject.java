package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity(name = "Subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String description;

    public Subject() {
    }

    public Subject(@NotNull String name, @NotNull String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                name.equals(subject.name) &&
                description.equals(subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
