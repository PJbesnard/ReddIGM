package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

import java.time.LocalDateTime;

public class VoteCommentDto {
    private Long commentId;
    private VoteType vote;
    private LocalDateTime creationDate;

    public VoteCommentDto(){}

    public VoteCommentDto(Long commentId, VoteType vote, LocalDateTime creationDate) {
        this.commentId = commentId;
        this.vote = vote;
        this.creationDate = creationDate;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setVote(VoteType vote) {
        this.vote = vote;
    }


    public Long getCommentId() {
        return commentId;
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
