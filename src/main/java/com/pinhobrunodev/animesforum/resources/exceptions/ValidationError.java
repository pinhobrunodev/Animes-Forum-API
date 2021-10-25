package com.pinhobrunodev.animesforum.resources.exceptions;


import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public void add(String fieldName, String msg) {
        errors.add(new FieldMessage(fieldName, msg));
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
