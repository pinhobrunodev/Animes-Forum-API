package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.reply.InsertReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.ShowReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.UpdateReplyDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicWithRepliesDTO;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.mapper.ReplyMapper;
import com.pinhobrunodev.animesforum.repositories.ReplyRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ForbiddenException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import com.pinhobrunodev.animesforum.services.exceptions.UnauthorizedException;
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
public class ReplyService {


    @Autowired
    private ReplyRepository repository;
    @Autowired
    private ReplyMapper mapper;
    @Autowired
    private AuthService authService;


    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','BASIC')")
    @Transactional
    public ShowReplyDTO save(InsertReplyDTO dto, Long topicId) {
        try {
            Reply reply = new Reply();
            reply = mapper.copyDtoToEntity(reply, dto, topicId);
            User user = authService.authenticated();
            if (user.getId() == reply.getTopic().getAuthor().getId()) {
                throw new UnauthorizedException("You can't reply your own Topic , only answer others replies");
            }
            repository.save(reply);
            return new ShowReplyDTO(reply);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }

    }

    @PreAuthorize("hasAnyRole('ADMIN','BASIC')")
    @Transactional
    public ShowReplyDTO update(Long replyId, UpdateReplyDTO dto) {
        try {

            Reply reply = mapper.updateReplyAux(replyId, dto);
            repository.save(reply);
            return new ShowReplyDTO(reply);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }
    }


    /**
     * TODO: Pensar em alguma forma de nao utilizar o @Trnsactional no delete
     */
    @Transactional
    public void delete(Long topicId) {
        try {
            Reply topic = repository.getOne(topicId);
            User author = authService.authenticated();

            if (topic.getReplyAuthor().equals(author)) {
                repository.deleteById(topicId);
            }

            if (topic.getReplyAuthor().getId() != author.getId() && author.hasRole("ROLE_ADMIN")) {
                repository.deleteById(topicId);
            }

            if (topic.getReplyAuthor().getId() != author.getId() && author.hasRole("ROLE_MODERATOR")) {
                repository.deleteById(topicId);
            }

            if (topic.getReplyAuthor().getId() != author.getId() && author.hasRole("ROLE_BASIC")) {
                throw new ForbiddenException("Access Denied");
            }

        } catch (EmptyResultDataAccessException | EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found : " + topicId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database violation");
        }
    }

    @Transactional(readOnly = true)
    public Page<ShowReplyDTO> pageAuthenticatedUserReplies(Pageable pageable) {
        User user = authService.authenticated();
        Page<Reply> result = repository.pageAuthenticatedUserReplies(user, pageable);
        return result.map(ShowReplyDTO::new);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','BASIC')")
    @Transactional
    public void likeReply(Long replyId) {
        Reply reply = likeReplies(replyId);
        repository.save(reply);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @Transactional(readOnly = true)
    public Page<ShowReplyDTO> showPagedReplyByTopicId(Long topicId, Pageable pageable) {
        Page<Reply> result = repository.showPagedReplyByTopicId(topicId, pageable);
        return result.map(ShowReplyDTO::new);
    }


    // Auxiliary services methods

    private Reply likeReplies(Long replyId) {
        try {
            User user = authService.authenticated();
            Reply reply = repository.getOne(replyId);
            if (reply.getReplyAuthor().getId() == user.getId()) {
                throw new UnauthorizedException("You cant Like your own Topic !!");
            }
            if (reply.getLikes().stream().anyMatch(x -> x.getId() == user.getId())) {
                throw new UnauthorizedException("You already liked that post");
            }
            reply.getLikes().add(user);
            reply.setQntLikes(Double.sum(reply.getQntLikes(), 1.0));
            return reply;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found : " + replyId);
        }

    }


}
