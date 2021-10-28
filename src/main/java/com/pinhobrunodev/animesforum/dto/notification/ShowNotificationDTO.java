package com.pinhobrunodev.animesforum.dto.notification;


import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.Notification;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.Topic;
import com.pinhobrunodev.animesforum.entities.User;

import java.time.Instant;

public class ShowNotificationDTO {


    private Long id;
    private String topicTitle;
    private String replyBody;
    private String replyAuthor;
    private boolean read;
    private String createdAt;

    public ShowNotificationDTO() {

    }

    public ShowNotificationDTO(Notification entity) {
        id = entity.getId();
        topicTitle = entity.getTitle();
        replyBody = entity.getReplyBody();
        for(Topic topic : entity.getTopicAuthor().getMyTopics()){
            for(Reply r : topic.getReplies()){
                replyAuthor = r.getReplyAuthor().getNickname();
            }
        }
        read = entity.isRead();
        createdAt = FormatInstant.format(entity.getCreatedAt());
    }

    public Long getId() {
        return id;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getReplyBody() {
        return replyBody;
    }

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public boolean isRead() {
        return read;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
