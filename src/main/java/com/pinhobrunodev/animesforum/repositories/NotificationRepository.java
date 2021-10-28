package com.pinhobrunodev.animesforum.repositories;

import com.pinhobrunodev.animesforum.entities.Notification;
import com.pinhobrunodev.animesforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification,Long> {


    @Query("SELECT obj FROM Notification obj WHERE obj.topicAuthor = :user AND obj.read = false")
    Page<Notification> findCurrentUserUnreadNotifications(User user, Pageable pageable);

    @Query("SELECT obj FROM Notification obj WHERE obj.topicAuthor = :user AND obj.read = true")
    Page<Notification> findCurrentUserReadNotifications(User user, Pageable pageable);


}
