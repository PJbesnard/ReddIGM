package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long commentId;
    private String text;
    private LocalDateTime creationDate;
    private Long postId;
    private String userName;
    private Long superCommentId;
    private String picture;
    private int nbVote;
    private VoteType myVote;
    private Integer nbComments;

    public CommentResponseDto() {
    }

    public CommentResponseDto(Long commentId, String picture, String text, LocalDateTime creationDate, Long postId, String userName, Long superCommentId, int nbVote, VoteType myVote, Integer nbComments) {
        this.commentId = commentId;
        this.text = text;
        this.creationDate = creationDate;
        this.postId = postId;
        this.userName = userName;
        this.superCommentId = superCommentId;
        this.picture = picture;
        this.nbVote = nbVote;
        this.myVote = myVote;
        this.nbComments = nbComments;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSuperCommentId() {
        return superCommentId;
    }

    public void setSuperCommentId(Long superCommentId) {
        this.superCommentId = superCommentId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public VoteType getMyVote() {
        return myVote;
    }

    public void setMyVote(VoteType myVote) {
        this.myVote = myVote;
    }

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", postId=" + postId +
                ", userName='" + userName + '\'' +
                ", superCommentId=" + superCommentId +
                ", picture='" + picture + '\'' +
                ", nbVote=" + nbVote +
                ", myVote=" + myVote +
                ", nbComments=" + nbComments +
                '}';
    }
}
