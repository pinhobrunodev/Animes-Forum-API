package com.pinhobrunodev.animesforum.dto.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.dto.reply.ReplyDTO;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Reply;
import com.pinhobrunodev.animesforum.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShowAnswerDTO {

    private Long id;
    private String topicAuthor;
    private String body;
    private String answeredAuthor;
    private String replyAuthor;
    private String answeredAt;
    private List<ReplyDTO> replies = new ArrayList<>();

    @Autowired
    private AuthService service;

    public ShowAnswerDTO() {
    }


    public ShowAnswerDTO(Answer entity, Set<Reply> repliesEntity) {
        id = entity.getId();
        body = entity.getBody();
        topicAuthor = entity.getTopicAuthor().getNickname();
        for(Reply r : entity.getReplies()){
            replyAuthor = r.getReplyAuthor().getNickname();
        }
        answeredAt = FormatInstant.format(entity.getCreatedAt());
        repliesEntity.forEach(x -> replies.add(new ReplyDTO(x)));
    }

    public ShowAnswerDTO(Answer entity, Set<Reply> repliesEntity, String answeredAuthor ){
        this(entity,repliesEntity);
        setAnsweredAuthor(answeredAuthor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(String topicAuthor) {
        this.topicAuthor = topicAuthor;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnsweredAuthor() {
        return answeredAuthor;
    }

    public void setAnsweredAuthor(String answeredAuthor) {
        this.answeredAuthor = answeredAuthor;
    }

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(String replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public String getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(String answeredAt) {
        this.answeredAt = answeredAt;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }

}
