package com.pinhobrunodev.animesforum.validations.topic;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopicUpdateValidator implements ConstraintValidator<TopicUpdateValid, UpdateTopicDTO> {

    @Autowired
    private TopicRepository repository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(TopicUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UpdateTopicDTO dto, ConstraintValidatorContext context) {

        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long topicId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();


        Topic topic = repository.findByTitle(dto.getTitle());
        if (topic != null && topicId != topic.getId()) {
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
