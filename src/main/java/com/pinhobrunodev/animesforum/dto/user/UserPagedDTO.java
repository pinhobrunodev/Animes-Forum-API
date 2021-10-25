package com.pinhobrunodev.animesforum.dto.user;

import com.pinhobrunodev.animesforum.entities.User;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.Instant;

public class UserPagedDTO extends UserDTO {

    @JsonProperty("registred_at")
    private Instant createdAt;

    public UserPagedDTO() {

    }

    public UserPagedDTO(User entity){
        createdAt = entity.getCreatedAt();
    }

    public UserPagedDTO(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
