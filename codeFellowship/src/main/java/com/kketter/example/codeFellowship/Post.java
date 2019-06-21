package com.kketter.example.codeFellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    //many posts, one creator
    @ManyToOne
    ApplicationUser creator;
    String body;
    //create timestamp for post and add to db
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public long getId() {
        return this.id;
    }

    public ApplicationUser getCreator() {
        return this.creator;
    }

    public String getBody() {
        return this.body;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
}
