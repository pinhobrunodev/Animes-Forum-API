package com.pinhobrunodev.animesforum.dto.reply;

import javax.validation.constraints.NotBlank;

public class UpdateReplyDTO {

    @NotBlank(message = "Mandatory field.")
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
