package com.pinhobrunodev.animesforum.dto.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.Reply;

public class ReplyDTO {
    private Long id;
    private String body;
    private String replyAuthor;
    @JsonProperty("reply_qnt_likes")
    private Double qntLikes;
    private String createdAt;


    public ReplyDTO() {
    }


    public ReplyDTO(Reply entity) {
        id = entity.getId();
        body = entity.getBody();
        replyAuthor = entity.getReplyAuthor().getNickname();
        qntLikes = entity.getQntLikes();
        createdAt = FormatInstant.format(entity.getCreatedAt());
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

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(String replyAuthor) {
        this.replyAuthor = replyAuthor;
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
