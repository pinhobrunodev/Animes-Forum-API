package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.mapper.GenderMapper;
import com.pinhobrunodev.animesforum.repositories.GenderRepository;
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
public class GenderServiceTests {


    @InjectMocks
    private GenderService service;
    @Mock
    private GenderRepository genderRepository;

    @Mock
    private GenderMapper mapper;

    private Gender gender;
    private GenderDTO genderDTO;

    private long existingId;
    private long nonExistingId;


    @BeforeEach
    public void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 999L;
        gender = Factory.createGender();
        genderDTO = Factory.createGenderDTO();

        Mockito.when(genderRepository.save(ArgumentMatchers.any())).thenReturn(gender);

        Mockito.doNothing().when(genderRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(genderRepository).deleteById(nonExistingId);

        Mockito.when(genderRepository.findById(existingId)).thenReturn(Optional.of(gender));
        Mockito.when(genderRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(genderRepository.getOne(existingId)).thenReturn(gender);
        Mockito.when(genderRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }


    @Test
    public void deleteShouldDeleteWhenExistingId() {
        service.delete(existingId);
        Mockito.verify(genderRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
        Mockito.verify(genderRepository, times(1)).deleteById(nonExistingId);

    }

    @Test
    public void findByIdShouldReturnGenderDTOWhenExistingId() {
        GenderDTO genderDTO = Factory.createGenderDTO();
        genderDTO = service.findById(existingId);
        Mockito.verify(genderRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            GenderDTO genderDTO = Factory.createGenderDTO();
            genderDTO = service.findById(nonExistingId);
        });
        Mockito.verify(genderRepository, times(1)).findById(nonExistingId);

    }

    @Test
    public void updateShouldReturnGenderDTOWhenExistingId() {
        genderDTO = service.update(existingId, new UpdateGenderDTO("GenderUpdate"));
    }


}
