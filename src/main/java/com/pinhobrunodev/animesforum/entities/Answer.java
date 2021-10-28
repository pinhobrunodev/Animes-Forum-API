package com.pinhobrunodev.animesforum.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_answer")
public class Answer  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE",name = "answeredAt")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;


    @ManyToOne
    @JoinColumn(name = "topic_author_id")
    private User topicAuthor;

    @ManyToMany
    @JoinTable(name = "tb_answer_reply"
            ,joinColumns = @JoinColumn(name = "answer_id")
            ,inverseJoinColumns = @JoinColumn(name = "reply_id"))
    private Set<Reply> replies = new HashSet<>();

    public Answer() {
    }

    public Answer(Long id, String body, Instant createdAt, Instant updatedAt, User topicAuthor) {
        this.id = id;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.topicAuthor = topicAuthor;
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

    public User getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(User topicAuthor) {
        this.topicAuthor = topicAuthor;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void PrePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void PreUpdate() {
        updatedAt = Instant.now();
    }

}
