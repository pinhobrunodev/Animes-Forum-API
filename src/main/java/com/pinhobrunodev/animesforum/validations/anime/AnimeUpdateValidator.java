package com.pinhobrunodev.animesforum.validations.anime;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.anime.UpdateAnimeDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimeUpdateValidator implements ConstraintValidator<AnimeUpdateValid, UpdateAnimeDTO> {


    @Autowired
    private AnimeRepository repository;
    @Autowired
    private HttpServletRequest request;


    @Override
    public void initialize(AnimeUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UpdateAnimeDTO dto, ConstraintValidatorContext context) {

        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long animeId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();


        Anime anime = repository.findByTitle(dto.getTitle());
        if (anime != null && anime.getId() != animeId) {
            list.add(new FieldMessage("title", "Anime Title already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
