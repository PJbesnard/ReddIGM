package fr.uge.jee.web.service.reddIGM.dto;

public class CommentRequestDto {
    private String text;
    private Long postId;
    private Long superCommentId;

    public CommentRequestDto(){}

    public CommentRequestDto(String text, Long postId, Long superCommentId) {
        this.text = text;
        this.postId = postId;
        this.superCommentId = superCommentId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getSuperCommentId() {
        return superCommentId;
    }

    public String getText() {
        return text;
    }

    public void setSuperCommentId(Long superCommentId) {
        this.superCommentId = superCommentId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
