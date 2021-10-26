package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {
}
