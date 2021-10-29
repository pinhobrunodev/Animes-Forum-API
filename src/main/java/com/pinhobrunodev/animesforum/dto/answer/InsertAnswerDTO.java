package com.pinhobrunodev.animesforum.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertAnswerDTO {

    private Long id;
    @NotBlank(message = "Mandatory field")
    private String body;
    @NotNull(message = "Reply ID must not be empty.")
    private Long replyId;
    private String answeredAuthor;


    public InsertAnswerDTO() {
    }

    public InsertAnswerDTO(Long id, String body, Long replyId) {
        this.id = id;
        this.body = body;
        this.replyId = replyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public InsertAnswerDTO(String answeredAuthor) {
        this.answeredAuthor = answeredAuthor;
    }
}
