package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.AnimeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimeRequestRepository extends JpaRepository<AnimeRequest,Long> {

    @Query("SELECT obj FROM AnimeRequest  obj WHERE obj.status = 0")
    Page<AnimeRequest> pagePendingAnimesRequests(Pageable pageable);

    @Query("SELECT obj FROM AnimeRequest  obj WHERE obj.status = 1")
    Page<AnimeRequest> pageApprovedAnimesRequests(Pageable pageable);

    @Query("SELECT obj FROM AnimeRequest  obj WHERE obj.status = 2")
    Page<AnimeRequest> pageDeniedAnimesRequests(Pageable pageable);
}
