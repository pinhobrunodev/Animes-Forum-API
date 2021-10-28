package com.pinhobrunodev.animesforum.dto.answer;

public class UpdateAnswerDTO {

    private  String body;
    private Long answerId;

    public UpdateAnswerDTO() {
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
