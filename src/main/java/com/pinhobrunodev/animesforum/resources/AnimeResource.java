package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.user.UserDTO;
import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserPagedDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.services.AnimeService;
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
@RequestMapping(value = "/animes")
public class AnimeResource {

    @Autowired
    private AnimeService service;


    @PostMapping(value = "/save")
    public ResponseEntity<AnimeDTO> save(@RequestBody AnimeDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimeDTO> update(@PathVariable Long id,  @RequestBody AnimeDTO dto){
        return ResponseEntity.ok().body(service.update(id,dto));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimeDTO> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(service.findById(id));
    }


    @GetMapping(path = "/page")
    ResponseEntity<Page<AnimeDTO>>loadAnimesPage(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "releaseDate", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        return  ResponseEntity.ok().body(service.paged(pageable));
    }

}
