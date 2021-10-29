package com.pinhobrunodev.animesforum.services;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.animerequest.AnimeRequestDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.AnimeRequest;
import com.pinhobrunodev.animesforum.enums.AnimeRequestStatus;
import com.pinhobrunodev.animesforum.mapper.AnimeRequestMapper;
import com.pinhobrunodev.animesforum.repositories.AnimeRequestRepository;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class AnimeRequestService {

    @Autowired
    private AnimeRequestRepository repository;

    @Autowired
    private AnimeRequestMapper mapper;

    @Transactional
    public void sendAnimeRequest(AnimeRequestDTO dto){
        AnimeRequest entity = new AnimeRequest();
        entity = mapper.copyDtoToEntity(entity,dto);
        repository.save(entity);
    }


    @Transactional
    public void setApproved(Long animeRequestId){
       try{
           AnimeRequest animeRequest = repository.getOne(animeRequestId);
           animeRequest.setStatus(AnimeRequestStatus.ACCEPTED);
           animeRequest.setAcceptedAt(LocalDateTime.now());
       }catch (EntityNotFoundException e){
           throw  new ResourceNotFoundException("Id not found : "+animeRequestId);
       }
    }

    @Transactional
    public void setDenied(Long animeRequestId){
        try{
            AnimeRequest animeRequest = repository.getOne(animeRequestId);
            animeRequest.setStatus(AnimeRequestStatus.DENIED);
            animeRequest.setDeniedAt(LocalDateTime.now());
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Id not found : "+animeRequestId);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found : " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database violation");
        }
    }

    @Transactional(readOnly = true)
    public Page<AnimeRequestDTO> pagePendingAnimesRequests(Pageable pageable){
        Page<AnimeRequest> result = repository.pagePendingAnimesRequests(pageable);
        return result.map(AnimeRequestDTO::new);
    }
    @Transactional(readOnly = true)
    public Page<AnimeRequestDTO> pageApprovedAnimesRequests(Pageable pageable){
        Page<AnimeRequest> result = repository.pageApprovedAnimesRequests(pageable);
        return result.map(AnimeRequestDTO::new);
    }
    @Transactional(readOnly = true)
    public Page<AnimeRequestDTO> pageDeniedAnimesRequests(Pageable pageable){
        Page<AnimeRequest> result = repository.pageDeniedAnimesRequests(pageable);
        return result.map(AnimeRequestDTO::new);
    }


}
