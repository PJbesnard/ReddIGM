package fr.uge.jee.web.service.reddIGM.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "userId", referencedColumnName = "userId")
//    private User user;
//    private LocalDateTime creationDate;
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private Subject subject;



    public Post(){};

    public Post(String postName){
        this.postName = postName;
//        this.creationDate = LocalDateTime.now();

    }

    public void setCreationDate(LocalDateTime creationDate) {
        //this.creationDate = creationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post comment = (Post) o;
        return Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }


}
