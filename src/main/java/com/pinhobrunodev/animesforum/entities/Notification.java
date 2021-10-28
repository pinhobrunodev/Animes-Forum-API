package com.pinhobrunodev.animesforum.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_notification")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String replyBody;
    private boolean read;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "topic_author_id")
    private User topicAuthor;



    public Notification() {
    }

    public Notification(Long id, String title, String replyBody, boolean read, User topicAuthor) {
        this.id = id;
        this.title = title;
        this.replyBody = replyBody;
        this.read = read;
        this.topicAuthor = topicAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplyBody() {
        return replyBody;
    }

    public void setReplyBody(String replyBody) {
        this.replyBody = replyBody;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User getTopicAuthor() {
        return topicAuthor;
    }

    public void setTopicAuthor(User topicAuthor) {
        this.topicAuthor = topicAuthor;
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
