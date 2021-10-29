package com.pinhobrunodev.animesforum.dto.gender;

import com.pinhobrunodev.animesforum.entities.Gender;

import javax.validation.constraints.NotBlank;

public class GenderDTO {

    private Long id;
    @NotBlank(message = "Mandatory field.")
    private String name;

    public GenderDTO(){

    }

    public GenderDTO(Gender entity){
        id = entity.getId();
        name = entity.getName();
    }

    public GenderDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
