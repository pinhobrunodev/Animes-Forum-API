package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.mapper.GenderMapper;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {

    @Autowired
    private GenderRepository repository;
    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private GenderMapper mapper;

    @Transactional
    public GenderDTO save(InsertGenderDTO dto) {
        Gender gender = new Gender();
        return new GenderDTO(repository.save(mapper.copyDtoToEntity(gender, dto)));
    }


    @Transactional
    public GenderDTO update(Long id, UpdateGenderDTO dto) {

        try {
            Gender gender = new Gender();
            return new GenderDTO(repository.save(mapper.updateGenderAux(id, dto)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }

    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found : " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database violation");
        }
    }

    /**
     *  User can choose an Anime By a Gender ID
     *
     *
     */

    @PreAuthorize("hasAnyRole('ADMIN','BASIC','MODERATOR')")
    @Transactional(readOnly = true)
    public Page<AnimeDTO> findAnimePagedByGenderId(Long id,Pageable pageable){
        Gender gender = repository.getOne(id);
        Page<Anime> result = animeRepository.findByGenders(gender,pageable);
        return  result.map(AnimeDTO::new);
    }




    @PreAuthorize("hasAnyRole('ADMIN','BASIC','MODERATOR')")
    @Transactional(readOnly = true)
    public List<GenderDTO> findAll() {
        return repository.findAll(Sort.by("name")).stream().map(GenderDTO::new).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional(readOnly = true)
    public GenderDTO findById(Long id) {
        return repository.findById(id).map(GenderDTO::new).orElseThrow(() -> new ResourceNotFoundException("Id not found : " + id));
    }


}
