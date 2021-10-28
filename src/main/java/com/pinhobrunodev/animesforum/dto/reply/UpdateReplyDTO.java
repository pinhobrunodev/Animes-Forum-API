package com.pinhobrunodev.animesforum.dto.reply;

public class UpdateReplyDTO {

    private String body;


    public UpdateReplyDTO() {
    }

    public UpdateReplyDTO(String body) {
        this.body = body;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
