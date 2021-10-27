package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicCreatedDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.services.TopicService;
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
@RequestMapping(value = "/topics")
public class TopicResource {

    @Autowired
    private TopicService service;

    @PostMapping(value = "/insert")
    public ResponseEntity<ShowTopicCreatedDTO> save(@RequestBody InsertTopicDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insertTopic(dto));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ShowTopicCreatedDTO> save(@PathVariable Long id,@RequestBody UpdateTopicDTO dto) {
        return ResponseEntity.ok().body(service.updateTopic(id,dto));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/page/my")
    public ResponseEntity<Page<ShowTopicCreatedDTO>> pageAuthenticatedUserTopics(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "title", direction = Sort.Direction.ASC)
            })  Pageable pageable) {
        return ResponseEntity.ok().body(service.pageAuthenticatedUserTopics(pageable));
    }

    @PostMapping(value = "/like/{id}")
    public ResponseEntity<Void> likeTopics(@PathVariable Long id){
        service.likeTopic(id);
        return ResponseEntity.noContent().build();
    }

}
