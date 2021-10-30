package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.anime.AnimeDTO;
import com.pinhobrunodev.animesforum.dto.anime.AnimeInsertDTO;
import com.pinhobrunodev.animesforum.dto.anime.UpdateAnimeDTO;
import com.pinhobrunodev.animesforum.dto.user.UserDTO;
import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserPagedDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.services.AnimeService;
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
@RequestMapping(value = "/animes")
public class AnimeResource {

    @Autowired
    private AnimeService service;

    @ApiOperation(value = "Salva um ANIME.")
    @PostMapping(value = "/save")
    public ResponseEntity<AnimeDTO> save(@Valid @RequestBody AnimeInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto));
    }

    @ApiOperation(value = "Atualiza um ANIME.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimeDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateAnimeDTO dto){
        return ResponseEntity.ok().body(service.update(id,dto));
    }

    @ApiOperation(value = "Deleta um ANIME.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Traz um ANIME pelo ID.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimeDTO> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value = "Lista de formaga paginada os ANIMES.")
    @GetMapping(path = "/page")
    ResponseEntity<Page<AnimeDTO>>loadAnimesPage(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "releaseDate", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        return  ResponseEntity.ok().body(service.paged(pageable));
    }

}
