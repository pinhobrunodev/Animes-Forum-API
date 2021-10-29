package com.pinhobrunodev.animesforum.services.validation.user;

import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
    @Autowired
    private UserRepository repository;
    @Autowired
    private HttpServletRequest request;
    @Override
    public void initialize(UserUpdateValid ann) {
    }


    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id")); // ''ID'' do endpoint  /{id}
        List<FieldMessage> list = new ArrayList<>();



        User user = repository.findByNickname(dto.getNickname());
        if (user != null && userId != user.getId()) {
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
