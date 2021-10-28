package com.pinhobrunodev.animesforum.dto.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Reply;

public class ShowMyAnswersPagedDTO {
    private Long id;
    private String topicName;
    private String body;
    @JsonProperty("answered_At")
    private String answeredAt;

    public ShowMyAnswersPagedDTO() {
    }

    public ShowMyAnswersPagedDTO(Answer entity){
        id = entity.getId();
        body = entity.getBody();
        answeredAt = FormatInstant.format(entity.getCreatedAt());
    }

    public ShowMyAnswersPagedDTO(Long id, String topicName, String body, String answeredAt) {
        this.id = id;
        this.topicName = topicName;
        this.body = body;
        this.answeredAt = answeredAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(String answeredAt) {
        this.answeredAt = answeredAt;
    }
}
