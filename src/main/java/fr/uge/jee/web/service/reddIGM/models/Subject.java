package fr.uge.jee.web.service.reddIGM.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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

    // TODO : Liste des Posts

    public Subject() {
    }

    public Subject(String name, String description) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
    }

    public String getName() {
        return name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return name.equals(subject.name) &&
                description.equals(subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
