package com.pinhobrunodev.animesforum.dto.answer;

import com.pinhobrunodev.animesforum.auxiliary.FormatInstant;
import com.pinhobrunodev.animesforum.entities.Answer;
import com.pinhobrunodev.animesforum.entities.Reply;

public class ShowAnswerTopicReplyDTO {



    private Long id;
    private String topicAuthor;
    private String body;
    private String answeredAuthor;
    private String replyAuthor;
    private String answeredAt;

    public ShowAnswerTopicReplyDTO() {
    }

    public ShowAnswerTopicReplyDTO(Answer entity) {
        id = entity.getId();
        body = entity.getBody();
        topicAuthor = entity.getTopicAuthor().getNickname();
        answeredAuthor = entity.getAnsweredAuthor();
        for(Reply r : entity.getReplies()){
            replyAuthor = r.getReplyAuthor().getNickname();
        }
        answeredAt = FormatInstant.format(entity.getCreatedAt());
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
}
