package com.pinhobrunodev.animesforum.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateAnswerDTO {

    @NotBlank(message = "Mandatory field")
    private String body;


    public UpdateAnswerDTO() {
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
