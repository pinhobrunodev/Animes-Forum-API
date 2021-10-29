package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.anime.AnimeInsertDTO;
import com.pinhobrunodev.animesforum.dto.anime.UpdateAnimeDTO;
import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimeMapper {

    @Autowired
    private AnimeRepository repository;
    @Autowired
    private GenderRepository genderRepository;

    public Anime copyDtoToEntity(Anime entity, AnimeInsertDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.getGenders().clear();
        for (GenderDTO genderDTO : dto.getGenders()) {
            Gender aux = genderRepository.getOne(genderDTO.getId());
            entity.getGenders().add(aux);
        }
        return entity;
    }


    public Anime updateAnimeAux(Long id, UpdateAnimeDTO dto) {
        Anime anime = repository.getOne(id);
        anime.setTitle(dto.getTitle());
        anime.setDescription(dto.getDescription());
        anime.setImgUrl(dto.getImgUrl());
        anime.setReleaseDate(dto.getReleaseDate());
        for (GenderDTO genderDTO : dto.getGenders()) {
            Gender aux = genderRepository.getOne(genderDTO.getId());
            anime.getGenders().add(aux);
        }
        return anime;
    }

}
