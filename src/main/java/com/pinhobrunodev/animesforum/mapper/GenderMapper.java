package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class GenderMapper {

    @Autowired
    private GenderRepository repository;


    public Gender copyDtoToEntity(Gender entity, InsertGenderDTO dto) {
        entity.setName(dto.getName());
        return entity;
    }

    public Gender updateGenderAux(Long id, UpdateGenderDTO dto) {
        Gender gender = repository.getOne(id);
        gender.setName(dto.getName());
        return gender;

    }

}
