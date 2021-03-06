package com.pinhobrunodev.animesforum.dto.user;


import com.pinhobrunodev.animesforum.validations.user.UserUpdateValid;

import javax.validation.constraints.NotBlank;

@UserUpdateValid
public class UserUpdateDTO{

    private String firstName;
    private String lastName;
    @NotBlank(message = "Please insert a valid nickname for update.")
    private String nickname;


    public UserUpdateDTO(){

    }


    public UserUpdateDTO(String firstName, String lastName, String nickname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
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
}
