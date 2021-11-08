package com.pinhobrunodev.animesforum.tests;

import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;

import java.time.Instant;
import java.time.LocalDate;

public class Factory {


    public static Gender createGender(){
        return new Gender(1L,"Gender", Instant.now(),Instant.now());
    }

    public static GenderDTO createGenderDTO(){
    return  new GenderDTO(createGender());
    }


    public static Anime createAnime(){
        Anime anime = new Anime(1L,"Title","description","imgUrl", LocalDate.now(),Instant.now(),Instant.now());
        anime.getGenders().add(createGender());
        return  anime;
    }


}
