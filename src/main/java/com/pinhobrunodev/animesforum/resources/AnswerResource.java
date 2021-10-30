package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.answer.InsertAnswerDTO;
import com.pinhobrunodev.animesforum.dto.answer.ShowAnswerDTO;
import com.pinhobrunodev.animesforum.dto.answer.ShowMyAnswersPagedDTO;
import com.pinhobrunodev.animesforum.dto.answer.UpdateAnswerDTO;
import com.pinhobrunodev.animesforum.services.AnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/answers")
public class AnswerResource {

    @Autowired
    private AnswerService service;

    @ApiOperation(value = "Regitra uma Resposta.")
    @PostMapping(value = "/insert")
    public ResponseEntity<ShowAnswerDTO> save(@Valid @RequestBody InsertAnswerDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto));
    }
    @ApiOperation(value = "Atuliza uma Resposta.")
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ShowAnswerDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateAnswerDTO dto) {
        return ResponseEntity.ok().body(service.updateAnswer(id, dto));
    }

    @ApiOperation(value = "Deleta uma Resposta.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Lista de forma paginada todas as respostas do usuário LOGADO.")
    @GetMapping(value = "/page/my")
    public ResponseEntity<Page<ShowMyAnswersPagedDTO>> ShowMyCurrentAnswers(Pageable pageable) {
        return ResponseEntity.ok().body(service.ShowMyCurrentAnswers(pageable));
    }

}
