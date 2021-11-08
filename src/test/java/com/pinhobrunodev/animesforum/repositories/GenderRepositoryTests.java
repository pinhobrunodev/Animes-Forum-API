package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class GenderRepositoryTests {

    @Autowired
    private GenderRepository genderRepository;

    private long existingId;
    private long nonExistingId;
    private long countTotalGenders;

    @BeforeEach
    public void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        countTotalGenders = 12L;
    }



    @DisplayName("Quando um ID for valido , deve trazer um optional carregado com o objeto.")
    @Test
    public void findByIdShouldReturnOptionalObjectWhenExistsId(){
        Optional<Gender> result = genderRepository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }
    @DisplayName("Quando um ID Não for válido ,  deve trazer um optional vazio.")
    @Test
    public void findByIdShouldReturnEmptyOptionalObjectWhenNonExistsId(){
        Optional<Gender> result = genderRepository.findById(nonExistingId);
        Assertions.assertFalse(result.isPresent());
    }

    @DisplayName("Quando passar um Id null , deve ser salvo.")
    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull(){
        Gender gender = Factory.createGender();
        gender.setId(null);
        genderRepository.save(gender);
        Assertions.assertEquals(countTotalGenders+1L,gender.getId());
    }
    @DisplayName("Quando um ID for válido , deve deletar o objeto e qnd buscar o id do mesmo , deve vim vazio.")
    @Test
    public void deleteShouldDeleteObjectWhenExistsId(){
        genderRepository.deleteById(existingId);
        Optional<Gender> result = genderRepository.findById(1L);
        Assertions.assertTrue(result.isEmpty());
    }
    @DisplayName("Quando um ID NÃO for válido , deve lançar uma EmptyResultDataAccessException.")
    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(EmptyResultDataAccessException.class,()->{
           genderRepository.deleteById(nonExistingId);
        });
    }


}
