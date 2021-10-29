package com.pinhobrunodev.animesforum.dto.reply;

import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.dto.answer.ShowAnswerTopicReplyDTO;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReplyDTO {
    private Long id;
    private String replyBody;
    private String replyAuthor;
    private Double replyLikes;
    private String createdAt;
    private List<ShowAnswerTopicReplyDTO> replyAnswers = new ArrayList<>();

    public ReplyDTO() {
    }


    public ReplyDTO(Reply entity, Set<Answer> answerEntity) {
        id = entity.getId();
        replyBody = entity.getBody();
        replyAuthor = entity.getReplyAuthor().getNickname();
        replyLikes = entity.getQntLikes();
        createdAt = FormatInstant.format(entity.getCreatedAt());
        answerEntity.forEach(x->replyAnswers.add(new ShowAnswerTopicReplyDTO(x)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyBody() {
        return replyBody;
    }

    public void setReplyBody(String replyBody) {
        this.replyBody = replyBody;
    }

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(String replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public Double getReplyLikes() {
        return replyLikes;
    }

    public void setReplyLikes(Double replyLikes) {
        this.replyLikes = replyLikes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<ShowAnswerTopicReplyDTO> getReplyAnswers() {
        return replyAnswers;
    }

}
