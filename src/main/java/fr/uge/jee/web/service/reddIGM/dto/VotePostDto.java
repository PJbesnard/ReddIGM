package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

public class VotePostDto {
    private Long postId;
    private VoteType vote;

    public VotePostDto(){}

    public VotePostDto(Long postId, VoteType vote) {
        this.postId = postId;
        this.vote = vote;
    }

    public void setCommentId(Long commentId) {
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

}

