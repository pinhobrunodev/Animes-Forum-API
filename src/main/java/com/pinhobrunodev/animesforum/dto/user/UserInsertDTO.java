package com.pinhobrunodev.animesforum.dto.user;

import com.pinhobrunodev.animesforum.services.validation.user.UserInsertValid;

@UserInsertValid
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
