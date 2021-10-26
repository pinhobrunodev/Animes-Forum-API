package com.pinhobrunodev.animesforum.mapper.gender;

import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    @Autowired
    private GenderRepository repository;


    public Gender copyDtoToEntity(Gender entity,GenderDTO dto){
        entity.setName(dto.getName());
        return entity;
    }
    public Gender updateGenderAux(Long id,GenderDTO dto){
        Gender gender = repository.getOne(id);
        gender.setName(dto.getName());
        return gender;
    }

}
