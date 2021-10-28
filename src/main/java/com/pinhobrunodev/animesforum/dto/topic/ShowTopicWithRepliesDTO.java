package com.pinhobrunodev.animesforum.dto.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.dto.reply.ReplyDTO;
import com.pinhobrunodev.animesforum.dto.reply.ShowReplyDTO;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class ShowTopicWithRepliesDTO {


    private Long id;
    private String topicName;
    private String topicCreatedBy;
    private String topicTheme;
    @JsonProperty("topic_qnt_likes")
    private Double qntLikes;
    private List<ReplyDTO> replies = new ArrayList<>();

    public ShowTopicWithRepliesDTO() {
    }

    public ShowTopicWithRepliesDTO(Topic entity, List<Reply> repliesEntity) {
        id = entity.getId();
        topicName = entity.getTitle();
        topicTheme = entity.getAnime().getTitle();
        topicCreatedBy = entity.getAuthor().getNickname();
        qntLikes = entity.getQntLikes();
        repliesEntity.forEach(x -> replies.add(new ReplyDTO(x)));
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

    public Double getQntLikes() {
        return qntLikes;
    }

    public void setQntLikes(Double qntLikes) {
        this.qntLikes = qntLikes;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }
}
