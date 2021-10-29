package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByNickname(String nickname);
}
