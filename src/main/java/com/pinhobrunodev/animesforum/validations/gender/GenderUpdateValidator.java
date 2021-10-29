package com.pinhobrunodev.animesforum.validations.gender;

import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenderUpdateValidator implements ConstraintValidator<GenderUpdateValid, UpdateGenderDTO> {

    @Autowired
    private GenderRepository repository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(GenderUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UpdateGenderDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long animeId = Long.parseLong(uriVars.get("id"));

        Gender gender = repository.findByName(dto.getName());
        if (gender != null && gender.getId() != animeId) {
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
