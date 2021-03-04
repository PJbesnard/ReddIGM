package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

public class VotePostDto {
    private Long commentId;
    private VoteType vote;

    public VotePostDto(){}

    public VotePostDto(Long commentId, VoteType vote) {
        this.commentId = commentId;
        this.vote = vote;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setVote(VoteType vote) {
        this.vote = vote;
    }


    public Long getPostId() {
        return commentId;
    }

    public VoteType getVote() {
        return vote;
    }

}

