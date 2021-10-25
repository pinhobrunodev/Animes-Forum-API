package com.pinhobrunodev.animesforum.services.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
