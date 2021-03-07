package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

import java.time.LocalDateTime;

public class VotePostDto {
    private Long postId;
    private VoteType vote;
    private LocalDateTime creationDate;

    public VotePostDto(){}

    public VotePostDto(Long postId, VoteType vote, LocalDateTime creationDate) {
        this.postId = postId;
        this.vote = vote;
        this.creationDate = creationDate;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setVote(VoteType vote) {
        this.vote = vote;
    }


    public Long getPostId() {
        return postId;
    }

    public VoteType getVote() {
        return vote;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

