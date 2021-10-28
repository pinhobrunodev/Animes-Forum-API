package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("SELECT obj FROM Reply  obj WHERE obj.replyAuthor = :replyAuthor")
    Page<Reply> pageAuthenticatedUserReplies(User replyAuthor, Pageable pageable);

    @Query("SELECT obj FROM Reply obj INNER  JOIN obj.topic t WHERE t.id = :topicId")
    Page<Reply> showPagedReplyByTopicId(Long topicId,Pageable pageable);

}
