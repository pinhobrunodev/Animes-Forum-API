package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.user.UserDTO;
import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserPagedDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * @apiNote Register User
     *
     */
    @Transactional
    public void save(UserInsertDTO dto){
        User entity = new User();
        repository.save(copyDtoToEntity(entity,dto));
    }

    /**
     * @apiNote Update User
     *
     */
    @Transactional
    public void update(Long userId,UserUpdateDTO dto){
        try {
            User entity = new User();
            repository.save(updateAux(userId,dto));
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Entity not found");
        }
    }

    /**
     * @apiNote Get authenticated User profile
     *
     */
    @Transactional(readOnly = true)
    public UserDTO getProfile(){
        return new UserDTO(service.authenticated());
    }


    /**
     * @apiNote Get paged users
     * @apiNote Only ROLE_ADMIN can access
     *
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional(readOnly = true)
    public Page<UserPagedDTO> pagedSearch(Pageable pageable){
        Page<User> page = repository.findAll(pageable);
        return page.map(UserPagedDTO::new);
    }





    // Auxiliary methods

    private User copyDtoToEntity(User entity, UserInsertDTO dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setNickname(dto.getNickname());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return entity;
    }

    private User updateAux (Long userId, UserUpdateDTO dto){
        User entity = repository.getOne(userId);
        service.validateSelfOrOther(entity.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNickname(dto.getNickname());
        entity.setEmail(dto.getEmail());
        return entity;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            logger.error("User not found: " + email);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + email);
        return user;
    }



}
