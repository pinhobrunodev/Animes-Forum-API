package com.pinhobrunodev.animesforum.dto.user;

import com.pinhobrunodev.animesforum.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class UserPagedDTO extends UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    @JsonProperty("registred_at")
    private Instant createdAt;

    public UserPagedDTO() {

    }
    public UserPagedDTO(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public UserPagedDTO(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        nickname = entity.getNickname();
        email = entity.getEmail();
        createdAt = entity.getCreatedAt();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
