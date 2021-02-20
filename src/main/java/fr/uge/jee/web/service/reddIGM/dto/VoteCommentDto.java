package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

public class VoteCommentDto {
    private Long commentId;
    private VoteType vote;
    private String userName;

    public VoteCommentDto(){}

    public VoteCommentDto(Long commentId, VoteType vote, String username) {
        this.commentId = commentId;
        this.vote = vote;
        this.userName = username;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setVote(VoteType vote) {
        this.vote = vote;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCommentId() {
        return commentId;
    }

    public VoteType getVote() {
        return vote;
    }

    public String getUsername() {
        return userName;
    }
}
