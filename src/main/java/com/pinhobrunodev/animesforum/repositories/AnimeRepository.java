package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Long> {

    Page<Anime> findByGenders(Gender gender, Pageable pageable);

    Anime findByTitle(String title);
}
