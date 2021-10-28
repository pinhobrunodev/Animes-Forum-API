package com.pinhobrunodev.animesforum.dto.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.entities.Topic;

import java.time.Instant;

public class ShowReplyDTO {

    private Long id;
    private String body;
    private String repliedBy;
    private String topicName;
    private String topicAuthor;
    @JsonProperty("reply_qnt_likes")
    private Double qntLikes;
    private String createdAt;

    public ShowReplyDTO() {
    }

    public ShowReplyDTO(Reply entity) {
        id = entity.getId();
        body = entity.getBody();
        repliedBy = entity.getReplyAuthor().getNickname();
        topicName = entity.getTopic().getTitle();
        topicAuthor = entity.getTopic().getAuthor().getNickname();
        qntLikes = entity.getQntLikes();
        createdAt = FormatInstant.format(entity.getCreatedAt());
    }


    public ShowReplyDTO(Long id, String body, String repliedBy, String topicName, String topicAuthor, Double qntLikes, String createdAt) {
        this.id = id;
        this.body = body;
        this.repliedBy = repliedBy;
        this.topicName = topicName;
        this.topicAuthor = topicAuthor;
        this.qntLikes = qntLikes;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(String repliedBy) {
        this.repliedBy = repliedBy;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(String topicAuthor) {
        this.topicAuthor = topicAuthor;
    }

    public Double getQntLikes() {
        return qntLikes;
    }

    public void setQntLikes(Double qntLikes) {
        this.qntLikes = qntLikes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}