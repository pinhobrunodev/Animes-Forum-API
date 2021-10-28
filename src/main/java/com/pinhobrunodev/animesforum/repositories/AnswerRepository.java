package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {



    @Query("SELECT  obj FROM Answer  obj INNER  JOIN  obj.topicAuthor author WHERE  author = :author")
    Page<Answer> findByTopicAuthorBasic(User author, Pageable pageable);

   /*

    TODO : Make a SQL QUERY that bring ADM OR MOD ANSWERS

   @Query("SELECT  obj FROM Answer obj INNER  JOIN obj.topicAuthor t where  t.nickname = :nickname")
    Page<Answer> findByNicknameAuthorAdminOrModerator(String nickname, Pageable pageable);*/
}
