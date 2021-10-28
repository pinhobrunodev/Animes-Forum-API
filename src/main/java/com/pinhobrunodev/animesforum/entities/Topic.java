package com.pinhobrunodev.animesforum.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_topic")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    private Double qntLikes;
    private String  createdBy;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany
    @JoinTable(name = "tb_topic_like",joinColumns = @JoinColumn(name = "topic_id")
            ,inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    public Topic() {
    }

    public Topic(Long id, String title, String body, Double qntLikes, String createdBy, Instant createdAt, Instant updatedAt, Anime anime, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.qntLikes = qntLikes;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.anime = anime;
        this.author = author;
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

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public List<Reply> getReplies() {
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
        qntLikes = 0.0;
    }

    @PreUpdate
    public void PreUpdate() {
        updatedAt = Instant.now();
    }


}
