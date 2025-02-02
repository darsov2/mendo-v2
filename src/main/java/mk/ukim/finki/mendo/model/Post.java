package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Post extends BaseAuditedEntity<Long> {

    LocalDateTime timestamp;
    Integer upvote;
    String description;
    @ManyToOne
    Post parent;
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Thread thread;

    public Post(String description, Post parent, Thread thread, MendoUser mendoUser) {
        this.timestamp = LocalDateTime.now();
        this.upvote = 0;
        this.description = description;
        this.parent = parent;
        this.mendoUser = mendoUser;
        this.thread = thread;
    }
}