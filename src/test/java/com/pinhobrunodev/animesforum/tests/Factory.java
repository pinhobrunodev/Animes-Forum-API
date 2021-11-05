package com.pinhobrunodev.animesforum.tests;

import com.pinhobrunodev.animesforum.entities.Gender;

import java.time.Instant;

public class Factory {


    public static Gender createGender(){
        return new Gender(1L,"Gender", Instant.now(),Instant.now());
    }
}
