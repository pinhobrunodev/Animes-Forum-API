package com.pinhobrunodev.animesforum.validations.topic;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class TopicInsertValidator implements ConstraintValidator<TopicInsertValid, InsertTopicDTO> {

    @Autowired
    private TopicRepository repository;

    @Override
    public void initialize(TopicInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(InsertTopicDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        Topic topic = repository.findByTitle(dto.getTitle());
        if (topic != null) {
            list.add(new FieldMessage("title", "Topic Title already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
