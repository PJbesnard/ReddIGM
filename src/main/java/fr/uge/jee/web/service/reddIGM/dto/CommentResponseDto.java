package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long commentId;
    private String text;
    private LocalDateTime creationDate;
    private Long postId;
    private Long superCommentId;
    private int nbVote;
    private VoteType myVote;
    private Integer nbComments;
    private UserDto user;

    public CommentResponseDto() {
    }

    public CommentResponseDto(Long commentId, String text, LocalDateTime creationDate, Long postId, Long superCommentId, int nbVote, VoteType myVote, Integer nbComments, UserDto user) {
        this.commentId = commentId;
        this.text = text;
        this.creationDate = creationDate;
        this.postId = postId;
        this.superCommentId = superCommentId;
        this.nbVote = nbVote;
        this.myVote = myVote;
        this.nbComments = nbComments;
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Integer getNbComments() {
        return nbComments;
    }

    public void setNbComments(Integer nbComments) {
        this.nbComments = nbComments;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNbVote() {
        return nbVote;
    }

    public void setNbVote(int nbVote) {
        this.nbVote = nbVote;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getSuperCommentId() {
        return superCommentId;
    }

    public void setSuperCommentId(Long superCommentId) {
        this.superCommentId = superCommentId;
    }

    public VoteType getMyVote() {
        return myVote;
    }

    public void setMyVote(VoteType myVote) {
        this.myVote = myVote;
    }
}
