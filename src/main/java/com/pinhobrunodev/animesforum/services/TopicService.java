package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicCreatedDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.entities.Role;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.mapper.TopicMapper;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import com.pinhobrunodev.animesforum.services.exceptions.UnauthorizedException;
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
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public ShowTopicCreatedDTO insertTopic(InsertTopicDTO dto) {
        Topic topic = new Topic();
        topic = mapper.copyDtoToEntity(topic, dto);
        User user = userRepository.getOne(topic.getAuthor().getId());
        if (user.hasRole("ROLE_MODERATOR")) {
            throw new ForbiddenException("Access Denied");
        }
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

    @Transactional
    public void likeTopic(Long topicId) {
        Topic topic = likeTopics(topicId);
        repository.save(topic);
    }


    // Auxiliary services methods


    private Topic likeTopics(Long topicId) {
        try {
            Double topicLike = 0.0;
            User user = authService.authenticated();
            Topic topic = repository.getOne(topicId);
            if (topic.getAuthor().getId() == user.getId()) {
                throw new UnauthorizedException("You cant Like your own Topic !!");
            }
            //topic 1 user 2
            // se o id de usuairo ja estiver presente ta tabela de um topico que deu like
            if (topic.getLikes().stream().anyMatch(x->x.getId() == user.getId())) {
                throw new UnauthorizedException("You already liked that post");
            }

            topic.getLikes().add(user);
            topic.setQntLikes(Double.sum(topic.getQntLikes(), 1.0));
            return topic;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found : " + topicId);
        }

    }


}
