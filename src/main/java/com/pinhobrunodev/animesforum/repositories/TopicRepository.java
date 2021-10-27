package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    @Query("SELECT obj FROM Topic  obj WHERE obj.author = :author")
    Page<Topic> pageAuthenticatedUserTopics(User author, Pageable pageable);
}
