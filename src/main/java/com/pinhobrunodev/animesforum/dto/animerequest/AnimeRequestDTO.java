package com.pinhobrunodev.animesforum.dto.animerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.entities.AnimeRequest;
import com.pinhobrunodev.animesforum.enums.AnimeRequestStatus;

import javax.persistence.Column;
import java.time.Instant;

public class AnimeRequestDTO {

    private Long id;
    private String title;
    private AnimeRequestStatus status;
    @JsonProperty("sent_at")
    private Instant createdAt;
    @JsonProperty("accepted_at")
    private Instant acceptedAt;
    @JsonProperty("denied_at")
    private Instant deniedAt;

    public AnimeRequestDTO() {
    }

    public AnimeRequestDTO(AnimeRequest entity) {
        id = entity.getId();
        title = entity.getTitle();
        status = entity.getStatus();
        createdAt = entity.getCreatedAt();
        acceptedAt = entity.getAcceptedAt();
        deniedAt = entity.getDeniedAt();
    }

    public AnimeRequestDTO(Long id, String title, AnimeRequestStatus status, Instant createdAt, Instant acceptedAt, Instant deniedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
        this.acceptedAt = acceptedAt;
        this.deniedAt = deniedAt;
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

    public AnimeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(AnimeRequestStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Instant acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Instant getDeniedAt() {
        return deniedAt;
    }

    public void setDeniedAt(Instant deniedAt) {
        this.deniedAt = deniedAt;
    }
}
