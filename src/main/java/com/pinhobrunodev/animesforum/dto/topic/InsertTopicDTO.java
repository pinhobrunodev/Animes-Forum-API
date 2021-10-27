package com.pinhobrunodev.animesforum.dto.topic;

import javax.persistence.Column;

public class InsertTopicDTO {

    private Long id;
    private String title;
    private String body;
    private Long animeId;

    public InsertTopicDTO() {

    }

    public InsertTopicDTO(Long id, String title, String body, Long animeId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.animeId = animeId;
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

    public void setBody(String body) {
        this.body = body;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

}
