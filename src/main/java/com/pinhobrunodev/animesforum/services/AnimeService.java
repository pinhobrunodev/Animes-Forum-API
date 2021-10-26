package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.mapper.AnimeMapper;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository repository;
    @Autowired
    private AnimeMapper mapper;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    public AnimeDTO save(AnimeDTO dto) {
        Anime anime = new Anime();
        anime = mapper.copyDtoToEntity(anime, dto);
        repository.save(anime);
        return new AnimeDTO(anime, anime.getGenders());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    public AnimeDTO update(Long id, AnimeDTO dto) {
        Anime anime = new Anime();
        anime = mapper.updateAnimeAux(id, dto);
        repository.save(anime);
        return new AnimeDTO(anime, anime.getGenders());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found : " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException ("Database violation");
        }
    }


    @Transactional(readOnly = true)
    public AnimeDTO findById(Long id) {
        return repository.findById(id).map(x -> new AnimeDTO(x, x.getGenders())).orElseThrow(() -> new ResourceNotFoundException("Anime Id not found : " + id));
    }

    @Transactional(readOnly = true)
    public Page<AnimeDTO> paged(Pageable pageable) {
        Page<Anime> result = repository.findAll(pageable);
        return result.map(AnimeDTO::new);
    }


}
