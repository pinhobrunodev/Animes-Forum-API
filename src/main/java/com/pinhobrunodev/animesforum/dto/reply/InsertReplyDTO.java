package com.pinhobrunodev.animesforum.dto.reply;

public class InsertReplyDTO {

    private Long id;
    private String body;

    public InsertReplyDTO() {
    }

    public InsertReplyDTO(Long id, String body) {
        this.id = id;
        this.body = body;
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
}
