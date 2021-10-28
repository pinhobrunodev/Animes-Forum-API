package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.reply.InsertReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.UpdateReplyDTO;
import com.pinhobrunodev.animesforum.entities.*;
import com.pinhobrunodev.animesforum.repositories.NotificationRepository;
import com.pinhobrunodev.animesforum.repositories.ReplyRepository;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.services.AuthService;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ReplyMapper {


    @Autowired
    private AuthService authService;
    @Autowired
    private ReplyRepository repository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private NotificationRepository notificationRepository;


    @Transactional
    public Reply copyDtoToEntity(Reply entity, InsertReplyDTO dto, Long topicId) {
        Notification notification = new Notification();
        Optional<Topic> result = topicRepository.findById(topicId);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Topic ID not found : " + topicId);
        }
        Topic topic = topicRepository.getOne(topicId);
        User replyAuthor = authService.authenticated();
        entity.setBody(dto.getBody());
        entity.setTopic(topic);
        entity.setReplyAuthor(replyAuthor);

        notification.setRead(false);
        notification.setReplyBody(entity.getBody());
        notification.setTitle(topic.getTitle());
        notification.setTopicAuthor(topic.getAuthor());

        notificationRepository.save(notification);
        return entity;
    }

    public Reply updateReplyAux(Long replyId, UpdateReplyDTO dto) {
        User user = authService.authenticated();
        if (user.getMyReplies().stream().filter(x -> x.getId() == replyId).findFirst().orElse(null) == null) {
            throw new ForbiddenException("Access Denied");
        }
        Optional<Reply> result = repository.findById(replyId);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Reply Id not found : " + replyId);
        }
        Reply reply = repository.getOne(replyId);
        reply.setBody(dto.getBody());
        return reply;
    }


}
