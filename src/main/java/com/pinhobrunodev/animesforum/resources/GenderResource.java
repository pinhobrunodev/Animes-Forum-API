package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/genders")
public class GenderResource {

    @Autowired
    private GenderService service;


    @PostMapping(value = "/save")
    public ResponseEntity<GenderDTO> save (@RequestBody GenderDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenderDTO> update (@PathVariable Long id , @RequestBody GenderDTO dto){
        return  ResponseEntity.ok().body(service.update(id,dto));
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<GenderDTO>> findAll(){
        return  ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenderDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

}
