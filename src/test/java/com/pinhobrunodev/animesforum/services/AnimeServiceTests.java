package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.anime.UpdateAnimeDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.mapper.AnimeMapper;
import com.pinhobrunodev.animesforum.repositories.AnimeRepository;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import com.pinhobrunodev.animesforum.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTests {


    @InjectMocks
    private AnimeService service;
    @Mock
    private AnimeRepository animeRepository;

    private Anime anime;
    private AnimeDTO animeDTO;

    private long existingId;
    private long nonExistingId;


    @BeforeEach
    public void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 999L;
        anime = Factory.createAnime();
        animeDTO = Factory.createAnimeDTO();

        Mockito.when(animeRepository.save(ArgumentMatchers.any())).thenReturn(anime);

        Mockito.doNothing().when(animeRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(animeRepository).deleteById(nonExistingId);

        Mockito.when(animeRepository.findById(existingId)).thenReturn(Optional.of(anime));
        Mockito.when(animeRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(animeRepository.getOne(existingId)).thenReturn(anime);
        Mockito.when(animeRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }


    @Test
    public void deleteShouldDeleteWhenExistingId() {
        service.delete(existingId);
        Mockito.verify(animeRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        Mockito.verify(animeRepository, times(1)).deleteById(nonExistingId);

    }

    @Test
    public void findByIdShouldReturnAnimeDTOWhenExistingId() {
        AnimeDTO animeDTO = Factory.createAnimeDTO();
        animeDTO = service.findById(existingId);
        Mockito.verify(animeRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            AnimeDTO animeDTO = Factory.createAnimeDTO();
            animeDTO = service.findById(nonExistingId);
        });
        Mockito.verify(animeRepository, times(1)).findById(nonExistingId);

    }



}
