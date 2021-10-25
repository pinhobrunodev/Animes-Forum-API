package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.user.UserDTO;
import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserPagedDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.services.UserService;
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
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(  @RequestBody UserInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        service.save(dto);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,  @RequestBody UserUpdateDTO dto) {
        service.update(id,dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getProfile (){
        return ResponseEntity.ok().body(service.getProfile());
    }

    @GetMapping(path = "/page")
    ResponseEntity<Page<UserPagedDTO>>loadUserPage(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        return  ResponseEntity.ok().body(service.pagedSearch(pageable));
    }
}