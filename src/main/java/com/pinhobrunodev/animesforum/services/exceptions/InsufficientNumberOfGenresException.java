package com.pinhobrunodev.animesforum.services.exceptions;

public class InsufficientNumberOfGenresException extends RuntimeException{
    public  InsufficientNumberOfGenresException(String msg){
        super(msg);
    }
}
