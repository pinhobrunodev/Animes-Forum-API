package com.pinhobrunodev.animesforum.dto.topic;

import com.pinhobrunodev.animesforum.dto.reply.ReplyDTO;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class ShowTopicWithRepliesAndAnswersDTO {

    private Long id;
    private String topicName;
    private String topicCreatedBy;
    private String topicTheme;
    private Double topicLikes;
    private List<ReplyDTO> topicReplies = new ArrayList<>();

    public ShowTopicWithRepliesAndAnswersDTO() {
    }
    public ShowTopicWithRepliesAndAnswersDTO(Topic entity, List<Reply> repliesEntity) {
        id = entity.getId();
        topicName = entity.getTitle();
        topicTheme = entity.getAnime().getTitle();
        topicLikes = entity.getQntLikes();
        repliesEntity.forEach(x-> topicReplies.add(new ReplyDTO(x,x.getAnswers())));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicCreatedBy() {
        return topicCreatedBy;
    }

    public void setTopicCreatedBy(String topicCreatedBy) {
        this.topicCreatedBy = topicCreatedBy;
    }

    public String getTopicTheme() {
        return topicTheme;
    }

    public void setTopicTheme(String topicTheme) {
        this.topicTheme = topicTheme;
    }

    public Double getTopicLikes() {
        return topicLikes;
    }

    public void setTopicLikes(Double topicLikes) {
        this.topicLikes = topicLikes;
    }

    public List<ReplyDTO> getTopicReplies() {
        return topicReplies;
    }

}
