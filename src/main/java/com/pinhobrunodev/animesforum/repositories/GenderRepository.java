package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Long> {
}
