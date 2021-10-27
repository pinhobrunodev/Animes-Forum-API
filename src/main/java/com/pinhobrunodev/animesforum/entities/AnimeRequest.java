package com.pinhobrunodev.animesforum.entities;

import com.pinhobrunodev.animesforum.enums.AnimeRequestStatus;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_anime_request")
public class AnimeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String title;
    private AnimeRequestStatus status;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant acceptedAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant deniedAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    public AnimeRequest() {
    }

    public AnimeRequest(Long id, String title, AnimeRequestStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void PrePersist() {
        createdAt = Instant.now();
    }

}
