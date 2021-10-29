package com.pinhobrunodev.animesforum.validations.anime;

import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.services.exceptions.InsufficientNumberOfGenresException;

public class AnimeInsertGenderValidation {


    public static void validation(Anime anime){
        if(anime.getGenders().size() <= 0){
            throw  new InsufficientNumberOfGenresException("Minimum of Gender is 1");
        }
    }

}
