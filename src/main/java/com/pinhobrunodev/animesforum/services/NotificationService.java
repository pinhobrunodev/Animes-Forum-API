package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.notification.ShowNotificationDTO;
import com.pinhobrunodev.animesforum.entities.Notification;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.NotificationRepository;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import com.pinhobrunodev.animesforum.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private AuthService service;



    @PreAuthorize("hasAnyRole('BASIC')")
    @Transactional
    public void setNotificationRead(Long notificationId){
      try{
          User user  = service.authenticated();
          Notification notification = repository.getOne(notificationId);
          if(notification.getTopicAuthor().getId() == user.getId()){
              notification.setRead(true);
          }
      }catch (EntityNotFoundException e){
          throw  new ResourceNotFoundException("Notification ID not found : "+notificationId);
      }catch (Exception e){
          throw  new UnauthorizedException("Invalid user");
      }

    }



    @PreAuthorize("hasAnyRole('BASIC')")
    @Transactional(readOnly = true)
    public Page<ShowNotificationDTO> findUnreadNotificationPagedByCurrentUser(Pageable pageable){
        User user  = service.authenticated();
        Page<Notification> result = repository.findCurrentUserUnreadNotifications(user,pageable);
        return result.map(ShowNotificationDTO::new);
    }

    @PreAuthorize("hasAnyRole('BASIC')")
    @Transactional(readOnly = true)
    public Page<ShowNotificationDTO> findReadNotificationPagedByCurrentUser(Pageable pageable){
        User user  = service.authenticated();
        Page<Notification> result = repository.findCurrentUserReadNotifications(user,pageable);
        return result.map(ShowNotificationDTO::new);
    }

}
