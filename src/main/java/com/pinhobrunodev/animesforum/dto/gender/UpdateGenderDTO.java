package com.pinhobrunodev.animesforum.dto.gender;


import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.validations.gender.GenderUpdateValid;

@GenderUpdateValid
public class UpdateGenderDTO extends GenderDTO{

    public UpdateGenderDTO(String name) {
        super(name);
    }

    public UpdateGenderDTO(Long id, String name) {
        super(id, name);
    }
}
