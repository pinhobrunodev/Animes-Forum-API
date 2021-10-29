package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.user.UserDTO;
import com.pinhobrunodev.animesforum.dto.user.UserInsertDTO;
import com.pinhobrunodev.animesforum.dto.user.UserPagedDTO;
import com.pinhobrunodev.animesforum.dto.user.UserUpdateDTO;
import com.pinhobrunodev.animesforum.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@Valid @RequestBody UserInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        service.save(dto);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,  @Valid @RequestBody UserUpdateDTO dto){
        service.update(id,dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(service.findById(id));
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
