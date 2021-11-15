package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {


    @Query("SELECT DISTINCT obj FROM Topic obj INNER JOIN obj.anime an WHERE (COALESCE(:animes) IS NULL OR an IN :animes) "
            + "AND (LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')))")
    Page<Topic> pageTopicByNameOrAnimeOrBoth(List<Anime> animes, String title, Pageable pageable);

    @Query("SELECT obj FROM Topic  obj WHERE obj.author = :author")
    Page<Topic> pageAuthenticatedUserTopics(User author, Pageable pageable);

    @Query("SELECT  obj FROM Topic  obj  WHERE obj.id = :topicId")
    Page<Topic> showPagedTopicWithRepliesByTopicId(Long topicId, Pageable pageable);

    Topic findByTitle(String title);


}
