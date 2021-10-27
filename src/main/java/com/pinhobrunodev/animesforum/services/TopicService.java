package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicCreatedDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.entities.Role;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.mapper.TopicMapper;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;


@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;
    @Autowired
    private TopicMapper mapper;
    @Autowired
    private AuthService authService;


    @Transactional
    public ShowTopicCreatedDTO insertTopic(InsertTopicDTO dto) {
        Topic topic = new Topic();
        topic = mapper.copyDtoToEntity(topic, dto);
        repository.save(topic);
        return new ShowTopicCreatedDTO(topic);
    }

    @Transactional
    public ShowTopicCreatedDTO updateTopic(Long topicId, UpdateTopicDTO dto) {
        Topic topic = mapper.updateTopicAux(topicId, dto);
        repository.save(topic);
        return new ShowTopicCreatedDTO(topic);
    }


    /**
     * TODO: Pensar em alguma forma de nao utilizar o @Trnsactional no delete
     */
    @Transactional
    public void delete(Long topicId) {
        try {
            Topic topic = repository.getOne(topicId);
            User author = authService.authenticated();

            if (topic.getAuthor().equals(author)) {
                repository.deleteById(topicId);
            }

            if (topic.getAuthor().getId() != author.getId() && author.hasRole("ROLE_ADMIN")) {
                repository.deleteById(topicId);
            }

            if (topic.getAuthor().getId() != author.getId() && author.hasRole("ROLE_MODERATOR")) {
                repository.deleteById(topicId);
            }

            if (topic.getAuthor().getId() != author.getId() && author.hasRole("ROLE_BASIC")) {
                throw new ForbiddenException("Access Denied");
            }

        } catch (EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found : " + topicId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database violation");
        }
    }

    @Transactional(readOnly = true)
    public Page<ShowTopicCreatedDTO> pageAuthenticatedUserTopics(Pageable pageable) {
        User user = authService.authenticated();
        Page<Topic> result = repository.pageAuthenticatedUserTopics(user, pageable);
        return result.map(ShowTopicCreatedDTO::new);
    }

}
