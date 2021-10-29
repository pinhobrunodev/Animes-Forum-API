package com.pinhobrunodev.animesforum.dto.topic;

import com.pinhobrunodev.animesforum.validations.topic.TopicUpdateValid;

import javax.validation.constraints.NotBlank;

@TopicUpdateValid
public class UpdateTopicDTO {
    @NotBlank(message = "Mandatory field.")
    private String title;
    @NotBlank(message = "Mandatory field.")
    private String body;


    public UpdateTopicDTO() {
    }

    public UpdateTopicDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
