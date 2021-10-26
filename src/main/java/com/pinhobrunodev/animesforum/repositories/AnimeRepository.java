package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime,Long> {
}
