package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.models.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private SubjectDto sub;
    private Integer voteCount;
    private LocalDateTime duration;
    private VoteType myVote;
    private UserDto user;

    public PostResponse(){}

    public PostResponse(Long id, String postName, String url, String description, SubjectDto sub, Integer voteCount, LocalDateTime duration, VoteType myVote, UserDto user) {
        this.id = id;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.sub = sub;
        this.voteCount = voteCount;
        this.duration = duration;
        this.myVote = myVote;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPostName() {
        return postName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public UserDto getUser() {
        return user;
    }

    public SubjectDto getSub() {
        return sub;
    }

    public VoteType getMyVote() {
        return myVote;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSub(SubjectDto sub) {
        this.sub = sub;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setMyVote(VoteType myVote) {
        this.myVote = myVote;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", sub=" + sub +
                ", voteCount=" + voteCount +
                ", duration=" + duration +
                ", myVote=" + myVote +
                ", user=" + user +
                '}';
    }
}