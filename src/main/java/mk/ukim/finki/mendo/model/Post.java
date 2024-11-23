package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime timestamp;
    Integer upvote;
    String description;
    @OneToMany
    List<Post> replies;
    @ManyToOne
    User user;
    @ManyToOne
    Thread thread;

}
