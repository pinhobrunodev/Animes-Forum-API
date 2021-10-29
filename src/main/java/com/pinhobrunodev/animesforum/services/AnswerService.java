package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.answer.InsertAnswerDTO;
import com.pinhobrunodev.animesforum.dto.answer.ShowAnswerDTO;
import com.pinhobrunodev.animesforum.dto.answer.ShowMyAnswersPagedDTO;
import com.pinhobrunodev.animesforum.dto.answer.UpdateAnswerDTO;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.mapper.AnswerMapper;
import com.pinhobrunodev.animesforum.repositories.AnswerRepository;
import com.pinhobrunodev.animesforum.repositories.TopicRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;
    @Autowired
    private AnswerMapper mapper;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private AuthService authService;


    //@PreAuthorize("hasAnyRole('BASIC')")
    @Transactional
    public ShowAnswerDTO save(InsertAnswerDTO dto) {
        User author = authService.authenticated();
        Answer answer = new Answer();
        answer = mapper.copyDtoToEntity(answer, dto);


        if (author.getMyTopics().stream().filter(x -> x.getAuthor().getId() == author.getId()).findFirst().orElse(null) == null
                && author.hasRole("ROLE_ADMIN")) {
            repository.save(answer);
            return new ShowAnswerDTO(answer);
        }

        if (author.getMyTopics().stream().filter(x -> x.getAuthor().getId() == author.getId()).findFirst().orElse(null) == null
                && author.hasRole("ROLE_MODERATOR")) {
            repository.save(answer);
            return new ShowAnswerDTO(answer);

        }

        if (author.getMyTopics().stream().filter(x -> x.getAuthor().getId() == author.getId()).findFirst().orElse(null) == null) {
            throw new ForbiddenException("Access Denied");
        }
        repository.save(answer);
        return new ShowAnswerDTO(answer);
    }


    @PreAuthorize("hasAnyRole('BASIC')")
    @Transactional
    public ShowAnswerDTO updateAnswer(Long answerId, UpdateAnswerDTO dto) {
        try {
            User author = authService.authenticated();
            Answer answer = mapper.updateAnswerAux(answerId, dto);
            repository.save(answer);
            return new ShowAnswerDTO(answer);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Answer Id not found : " + answerId);
        }
    }


    @Transactional
    public void delete(Long answerId) {
        try {
            User author = authService.authenticated();

            if (author.getAnswers().stream().filter(x -> x.getTopicAuthor().getId() == author.getId()).findFirst().orElse(null) != null) {
                repository.deleteById(answerId);
            }

            if (author.getAnswers().contains(answerId) && author.hasRole("ROLE_ADMIN")) {
                repository.deleteById(answerId);

            }

            if (author.getAnswers().contains(answerId) && author.hasRole("ROLE_MODERATOR")) {
                repository.deleteById(answerId);

            }

            if (author.getAnswers().stream().filter(x -> x.getTopicAuthor().getId() == author.getId()).findFirst().orElse(null) == null && author.hasRole("ROLE_BASIC")) {
                throw new ForbiddenException("Access Denied -- caiu aqui");
            }

        } catch (EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found : " + answerId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database violation");
        }
    }


    @Transactional(readOnly = true)
    public Page<ShowMyAnswersPagedDTO> ShowMyCurrentAnswers(Pageable pageable) {
        User author = authService.authenticated();
        Page<Answer> result = repository.findByTopicAuthorBasic(author.getNickname(), pageable);
        return result.map(ShowMyAnswersPagedDTO::new);
    }


   /* @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @Transactional(readOnly = true)
    public Page<ShowMyAnswersPagedDTO> ShowMyCurrentAnswersAdminOrModerator(Pageable pageable) {
        User author = authService.authenticated();
        Page<Answer> result = repository.findByNicknameAuthorAdminOrModerator(author.getNickname(), pageable);
        return result.map(ShowMyAnswersPagedDTO::new);
    }
    */

}
