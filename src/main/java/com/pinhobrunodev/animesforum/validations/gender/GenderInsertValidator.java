package com.pinhobrunodev.animesforum.validations.gender;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import com.pinhobrunodev.animesforum.validations.anime.AnimeInsertValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class GenderInsertValidator implements ConstraintValidator<GenderInsertValid, InsertGenderDTO> {

    @Autowired
    private GenderRepository repository;

    @Override
    public void initialize(GenderInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(InsertGenderDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        Gender gender = repository.findByName(dto.getName());
        if (gender != null) {
            list.add(new FieldMessage("name", "Gender Name already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
