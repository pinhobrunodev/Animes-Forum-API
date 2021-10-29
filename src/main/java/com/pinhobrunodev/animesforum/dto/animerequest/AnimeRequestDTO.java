package com.pinhobrunodev.animesforum.dto.animerequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.AnimeRequest;
import com.pinhobrunodev.animesforum.enums.AnimeRequestStatus;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AnimeRequestDTO {

    private Long id;
    @NotBlank(message = "Mandatory Field.")
    private String title;
    @NotBlank(message = "Mandatory Field.")
    private String body;
    private AnimeRequestStatus status;
    @JsonProperty("sent_at")
    private String sentAt;
    @JsonProperty("accepted_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime acceptedAt;
    @JsonProperty("denied_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deniedAt;

    public AnimeRequestDTO() {
    }

    public AnimeRequestDTO(AnimeRequest entity) {
        id = entity.getId();
        title = entity.getTitle();
        body = entity.getBody();
        status = entity.getStatus();
        sentAt = FormatInstant.format(entity.getCreatedAt());
        acceptedAt = entity.getAcceptedAt();
        deniedAt = entity.getDeniedAt();
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

    public AnimeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(AnimeRequestStatus status) {
        this.status = status;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getDeniedAt() {
        return deniedAt;
    }

    public void setDeniedAt(LocalDateTime deniedAt) {
        this.deniedAt = deniedAt;
    }
}
