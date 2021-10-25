package com.pinhobrunodev.animesforum.dto.user;


import com.pinhobrunodev.animesforum.dto.role.RoleDTO;
import com.pinhobrunodev.animesforum.entities.User;


public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;

    public UserDTO() {
    }

    public UserDTO(User entity) {
    id = entity.getId();
    firstName = entity.getFirstName();
    lastName = entity.getLastName();
    nickname = entity.getNickname();
    email = entity.getEmail();
    }

    public UserDTO(Long id, String firstName, String lastName, String nickname, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
