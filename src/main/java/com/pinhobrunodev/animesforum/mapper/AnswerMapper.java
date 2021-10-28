package com.pinhobrunodev.animesforum.mapper;

import com.pinhobrunodev.animesforum.dto.answer.InsertAnswerDTO;
import com.pinhobrunodev.animesforum.dto.answer.UpdateAnswerDTO;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.AnswerRepository;
import com.pinhobrunodev.animesforum.repositories.ReplyRepository;
import com.pinhobrunodev.animesforum.services.AuthService;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnswerMapper {

    @Autowired
    private AuthService authService;
    @Autowired
    private AnswerRepository repository;
    @Autowired
    private ReplyRepository replyRepository;


    public Answer copyDtoToEntity(Answer answer, InsertAnswerDTO dto) {
        User user = authService.authenticated();
        Optional<Reply> result = replyRepository.findById(dto.getReplyId());
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Reply ID not found : " + dto.getReplyId());
        }
        Reply reply = replyRepository.getOne(dto.getReplyId());
        answer.setBody(dto.getBody());
        answer.setTopicAuthor(reply.getTopic().getAuthor());
        answer.getReplies().add(reply);


        return answer;
    }

    public Answer updateAnswerAux(Long answerId, UpdateAnswerDTO dto) {
        User user = authService.authenticated();
        Optional<Answer> result = repository.findById(answerId);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Answer Id not found : " + answerId);
        }
        Answer answer = repository.getOne(answerId);
        if (user.getId() != answer.getTopicAuthor().getId()) {
            throw new ForbiddenException("Access Denied");

        }
        answer.setBody(dto.getBody());
        return answer;
    }

}
