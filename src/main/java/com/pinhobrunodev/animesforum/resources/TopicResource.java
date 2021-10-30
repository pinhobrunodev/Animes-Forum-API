package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicCreatedDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicWithRepliesAndAnswersDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.services.TopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/topics")
public class TopicResource {

    @Autowired
    private TopicService service;

    @ApiOperation(value = "Insere um Tópico.")
    @PostMapping(value = "/insert")
    public ResponseEntity<ShowTopicCreatedDTO> save(@Valid  @RequestBody InsertTopicDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insertTopic(dto));
    }
    @ApiOperation(value = "Atualiza um Tópico.")
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ShowTopicCreatedDTO> save(@PathVariable Long id, @Valid  @RequestBody UpdateTopicDTO dto) {
        return ResponseEntity.ok().body(service.updateTopic(id, dto));
    }

    @ApiOperation(value = "Deleta um Tópico.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Lista de forma Paginada os Tópicos do usuário que está LOGADO.")
    @GetMapping(value = "/page/my")
    public ResponseEntity<Page<ShowTopicCreatedDTO>> pageAuthenticatedUserTopics(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "title", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return ResponseEntity.ok().body(service.pageAuthenticatedUserTopics(pageable));
    }

    @ApiOperation(value = "Insere um Like  a um Tópico.")
    @PostMapping(value = "/like/{id}")
    public ResponseEntity<Void> likeTopics(@PathVariable Long id) {
        service.likeTopic(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Lista de forma Paginada o Tópico com base no Nome que foi passado como parâmetro.")
    @GetMapping(value = "/page/filter")
    public ResponseEntity<Page<ShowTopicCreatedDTO>> pageTopicByName(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "likes", direction = Sort.Direction.ASC)
            }) @RequestParam String topicName, Pageable pageable) {
        return ResponseEntity.ok().body(service.pageTopicByName(topicName, pageable));
    }


    @ApiOperation(value = "Lista de forma Paginada o Tópico com seus REPLIES e suas ANSWERS.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<ShowTopicWithRepliesAndAnswersDTO>> ShowTopicWithRepliesAndAnswersDTO(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "qntLikes", direction = Sort.Direction.DESC)
            }) @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok().body(service.ShowTopicWithRepliesAndAnswersDTO(id, pageable));
    }

}
