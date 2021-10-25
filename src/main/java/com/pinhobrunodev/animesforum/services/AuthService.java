package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;


    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repository.findByEmail(email);
            return user;
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelfOrOther(Long userId){
        User user = authenticated();
        if(user.getId() != userId){
            throw  new ForbiddenException("Access Denied");
        }
    }

}
