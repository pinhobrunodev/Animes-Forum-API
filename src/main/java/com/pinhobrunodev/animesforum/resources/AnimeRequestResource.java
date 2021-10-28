package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.animerequest.AnimeRequestDTO;
import com.pinhobrunodev.animesforum.services.AnimeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/anime-request")
public class AnimeRequestResource {

    @Autowired
    private AnimeRequestService service;


    @PostMapping(value = "/send")
    public ResponseEntity<Void> sendAnimeRequest(@RequestBody AnimeRequestDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        service.sendAnimeRequest(dto);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(value = "/set-approved/{id}")
    public ResponseEntity<Void> setApproved(@PathVariable Long id){
        service.setApproved(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/set-denied/{id}")
    public ResponseEntity<Void> setDenied(@PathVariable Long id){
        service.setDenied(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page/pending")
    public ResponseEntity<Page<AnimeRequestDTO>> pagePendingAnimesRequests(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC)
            })  Pageable pageable) {
        return ResponseEntity.ok().body(service.pagePendingAnimesRequests(pageable));
    }

    @GetMapping(value = "/page/approved")
    public ResponseEntity<Page<AnimeRequestDTO>> pageApprovedAnimesRequests(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "acceptedAt", direction = Sort.Direction.ASC)
            })  Pageable pageable) {
        return ResponseEntity.ok().body(service.pageApprovedAnimesRequests(pageable));
    }

    @GetMapping(value = "/page/denied")
    public ResponseEntity<Page<AnimeRequestDTO>> pageDeniedAnimesRequests(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "deniedAt", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return ResponseEntity.ok().body(service.pageDeniedAnimesRequests(pageable));
    }
}
