package com.pinhobrunodev.animesforum.dto.user;

public class UserInsertDTO extends UserDTO {

    private String password;

    public UserInsertDTO() {

    }

    public UserInsertDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
