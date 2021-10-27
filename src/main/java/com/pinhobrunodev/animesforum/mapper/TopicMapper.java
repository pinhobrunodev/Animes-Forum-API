package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.services.AuthService;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TopicMapper {


    @Autowired
    private TopicRepository repository;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    public Topic copyDtoToEntity(Topic entity, InsertTopicDTO dto) {

        Anime anime = animeRepository.getOne(dto.getAnimeId());
        User user = authService.authenticated();

        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        entity.setAnime(anime);
        entity.setAuthor(user);
        entity.setCreatedBy(user.getNickname());
        return entity;
    }

    public Topic updateTopicAux(Long topicId, UpdateTopicDTO dto) {
        User user = authService.authenticated();
        if (user.getMyTopics().stream().filter(x -> Objects.equals(x.getId(), topicId)).findFirst().orElse(null) == null) {
            throw new ForbiddenException("Access Denied");
        }
        Topic topic = repository.getOne(topicId);
        topic.setTitle(dto.getTitle());
        topic.setBody(dto.getBody());
        return topic;

    }


}
