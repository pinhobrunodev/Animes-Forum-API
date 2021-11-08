package com.pinhobrunodev.animesforum.dto.gender;


import com.pinhobrunodev.animesforum.validations.gender.GenderInsertValid;

@GenderInsertValid
public class InsertGenderDTO extends  GenderDTO{


    public InsertGenderDTO(Long id, String name) {
        super(id, name);
    }
}
