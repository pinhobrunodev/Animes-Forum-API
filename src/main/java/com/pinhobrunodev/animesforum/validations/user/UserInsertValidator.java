package com.pinhobrunodev.animesforum.validations.user;

import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }


    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        User user = repository.findByEmail(dto.getEmail());
        if (user != null) {
            list.add(new FieldMessage("email", "E-mail already exists"));
        }
        User user1 = repository.findByNickname(dto.getNickname());
        if (user1  != null){
            list.add(new FieldMessage("nickname", "Nickname already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
