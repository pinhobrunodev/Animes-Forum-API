package com.pinhobrunodev.animesforum.resources;

import com.pinhobrunodev.animesforum.dto.notification.ShowNotificationDTO;
import com.pinhobrunodev.animesforum.dto.reply.ShowReplyDTO;
import com.pinhobrunodev.animesforum.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationResource {

    @Autowired
    private NotificationService service;


    @PatchMapping(value = "/set-read/{id}")
    public ResponseEntity<Void> setNotificationRead(@PathVariable Long id) {
        service.setNotificationRead(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ShowNotificationDTO>> findUnreadNotificationPagedByCurrentUser(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "title", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return ResponseEntity.ok().body(service.findUnreadNotificationPagedByCurrentUser(pageable));
    }


    @GetMapping(value = "/read")
    public ResponseEntity<Page<ShowNotificationDTO>> findReadNotificationPagedByCurrentUser(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "title", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return ResponseEntity.ok().body(service.findReadNotificationPagedByCurrentUser(pageable));
    }

}
