package com.pinhobrunodev.animesforum.dto.answer;

public class InsertAnswerDTO {

    private Long id;
    private String body;
    private Long replyId;


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
}
