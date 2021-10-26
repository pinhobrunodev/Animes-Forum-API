package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.RoleRepository;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public User copyDtoToEntity(User entity, UserInsertDTO dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNickname(dto.getNickname());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        entity.getRoles().add((roleRepository.getOne(3L)));
        return entity;
    }

    public User updateAux(Long userId, UserUpdateDTO dto) {
        service.validateSelfOrAdmin(userId);
        User entity = repository.getOne(userId);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNickname(dto.getNickname());
        return entity;
    }
}