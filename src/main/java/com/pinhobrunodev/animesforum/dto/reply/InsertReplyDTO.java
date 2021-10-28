package com.pinhobrunodev.animesforum.dto.reply;

public class InsertReplyDTO {

    private Long id;
    private String body;
    private Long topicId;

    public InsertReplyDTO() {
    }

    public InsertReplyDTO(Long id, String body, Long topicId) {
        this.id = id;
        this.body = body;
        this.topicId = topicId;
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
