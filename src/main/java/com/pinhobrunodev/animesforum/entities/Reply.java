package com.pinhobrunodev.animesforum.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String body;
    private Double qntLikes;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User replyAuthor;

    @ManyToMany
    @JoinTable(name = "tb_reply_like"
            ,joinColumns = @JoinColumn(name = "reply_id")
            ,inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();



    public Reply() {
    }

    public Reply(Long id, String body, Double qntLikes, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.body = body;
        this.qntLikes = qntLikes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Double getQntLikes() {
        return qntLikes;
    }

    public void setQntLikes(Double qntLikes) {
        this.qntLikes = qntLikes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(User replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public Set<User> getLikes() {
        return likes;
    }

    @PrePersist
    public void PrePersist() {
        createdAt = Instant.now();
        qntLikes = 0.0;
    }

    @PreUpdate
    public void PreUpdate() {
        updatedAt = Instant.now();
    }
}
