package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.animerequest.AnimeRequestDTO;
import com.pinhobrunodev.animesforum.entities.AnimeRequest;
import com.pinhobrunodev.animesforum.enums.AnimeRequestStatus;
import com.pinhobrunodev.animesforum.repositories.AnimeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimeRequestMapper {

    @Autowired
    private AnimeRequestRepository repository;

    public AnimeRequest copyDtoToEntity(AnimeRequest entity, AnimeRequestDTO dto){
        entity.setStatus(AnimeRequestStatus.PENDING);
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        return entity;
    }



}
