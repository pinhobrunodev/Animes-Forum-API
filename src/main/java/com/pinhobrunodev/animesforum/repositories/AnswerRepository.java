package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {



    @Query("SELECT  obj FROM Answer  obj where obj.answeredAuthor = :answeredAuthor")
    Page<Answer> findByTopicAuthorBasic(String answeredAuthor, Pageable pageable);
}
