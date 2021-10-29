package com.pinhobrunodev.animesforum.validations.anime;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import com.pinhobrunodev.animesforum.validations.topic.TopicInsertValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AnimeInsertValidator implements ConstraintValidator<AnimeInsertValid, AnimeDTO> {


    @Autowired
    private AnimeRepository repository;

    @Override
    public void initialize(AnimeInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(AnimeDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        Anime anime = repository.findByTitle(dto.getTitle());
        if (anime != null) {
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
