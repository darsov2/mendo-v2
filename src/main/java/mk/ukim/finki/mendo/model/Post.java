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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime timestamp;
    Integer upvote;
    String description;
    @OneToMany
    List<Post> replies;
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Thread thread;

}
