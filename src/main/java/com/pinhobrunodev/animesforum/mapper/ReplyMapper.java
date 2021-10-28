package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.reply.InsertReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.UpdateReplyDTO;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.ReplyRepository;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.services.AuthService;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReplyMapper {


    @Autowired
    private AuthService authService;
    @Autowired
    private ReplyRepository repository;
    @Autowired
    private TopicRepository topicRepository;

    public Reply copyDtoToEntity(Reply entity, InsertReplyDTO dto) {
        Optional<Topic> result = topicRepository.findById(dto.getTopicId());
        if(result.isEmpty()){
            throw  new ResourceNotFoundException("Topic ID not found : "+dto.getTopicId());
        }
        Topic topic = topicRepository.getOne(dto.getTopicId());
        User replyAuthor = authService.authenticated();
        entity.setBody(dto.getBody());
        entity.setTopic(topic);
        entity.setReplyAuthor(replyAuthor);
        return entity;
    }

    public Reply updateReplyAux(Long replyId, UpdateReplyDTO dto) {
        User user = authService.authenticated();
        if (user.getMyReplies().stream().filter(x -> x.getId() == replyId).findFirst().orElse(null) == null) {
            throw new ForbiddenException("Access Denied");
        }
        Reply reply = repository.getOne(replyId);
        reply.setBody(dto.getBody());
        return reply;
    }


}
