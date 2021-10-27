package com.pinhobrunodev.animesforum.dto.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.entities.Topic;

public class ShowTopicCreatedDTO {

    @JsonProperty("topic_id")
    private Long id;
    private String title;
    private String body;
    private String animeName;
    private String createdBy;
    private Double qntLikes;

    public ShowTopicCreatedDTO() {
    }

    public ShowTopicCreatedDTO(Topic entity) {
        id = entity.getId();
        title = entity.getTitle();
        body = entity.getBody();
        animeName = entity.getAnime().getTitle();
        createdBy = entity.getCreatedBy();
        qntLikes = entity.getQntLikes();
    }

    public ShowTopicCreatedDTO(Long id, String title, String body, String animeName, String createdBy, Double qntLikes) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.animeName = animeName;
        this.createdBy = createdBy;
        this.qntLikes = qntLikes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getQntLikes() {
        return qntLikes;
    }

    public void setQntLikes(Double qntLikes) {
        this.qntLikes = qntLikes;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
