package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.UpdateGenderDTO;
import com.pinhobrunodev.animesforum.services.GenderService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/genders")
public class GenderResource {

    @Autowired
    private GenderService service;


    @PostMapping(value = "/save")
    public ResponseEntity<GenderDTO> save(@Valid @RequestBody InsertGenderDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenderDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateGenderDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<GenderDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/{id}/page/animes")
    public ResponseEntity<Page<AnimeDTO>> findAnimePagedByGenderId(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
            @SortDefault(sort = "title", direction = Sort.Direction.ASC)
            }) @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok().body(service.findAnimePagedByGenderId(id,pageable));
    }

}
