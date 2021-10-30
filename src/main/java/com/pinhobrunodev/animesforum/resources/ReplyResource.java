package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.reply.InsertReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.ShowReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.UpdateReplyDTO;
import com.pinhobrunodev.animesforum.dto.topic.InsertTopicDTO;
import com.pinhobrunodev.animesforum.dto.topic.ShowTopicCreatedDTO;
import com.pinhobrunodev.animesforum.dto.topic.UpdateTopicDTO;
import com.pinhobrunodev.animesforum.services.ReplyService;
import com.pinhobrunodev.animesforum.services.TopicService;
import io.swagger.annotations.ApiOperation;
import javassist.compiler.ast.Variable;
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
@RequestMapping(value = "/replies")
public class ReplyResource {

    @Autowired
    private ReplyService service;

    @ApiOperation(value = "Insere um REPLY a um Tópico.")
    @PostMapping(value = "/insert/topic/{id}")
    public ResponseEntity<ShowReplyDTO> save(@PathVariable Long id,@Valid @RequestBody InsertReplyDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto,id));
    }

    @ApiOperation(value = "Atualiza o Reply de um Tópico")
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ShowReplyDTO> save(@PathVariable Long id, @Valid @RequestBody UpdateReplyDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }


    @ApiOperation(value = "Deleta um REPLY de um Tópico.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Lista de forma paginada todos os Replies feitos do usuário que está LOGADO")
    @GetMapping(value = "/page/my")
    public ResponseEntity<Page<ShowReplyDTO>> pageAuthenticatedUserReplies(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "qntLikes", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return ResponseEntity.ok().body(service.pageAuthenticatedUserReplies(pageable));
    }


    @ApiOperation(value = "Lista todas a Replies de um Tópico específico pelo ID do Tópico.")
    @GetMapping(value = "/topic/{id}")
    public ResponseEntity<Page<ShowReplyDTO>> showPagedReplyByTopicId(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "qntLikes", direction = Sort.Direction.ASC)
            }) @PathVariable Long id,Pageable pageable) {
        return ResponseEntity.ok().body(service.showPagedReplyByTopicId(id,pageable));
    }


    @ApiOperation(value = "Insere um Like ao Reply")
    @PostMapping(value = "/like/{id}")
    public ResponseEntity<Void> likeReply(@PathVariable Long id) {
        service.likeReply(id);
        return ResponseEntity.noContent().build();
    }

}
