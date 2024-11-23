package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.SubmissionResult;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime timestamp;
    String language;
    @Enumerated(EnumType.STRING)
    SubmissionResult submissionResult;
    //todo Code
    @ManyToOne
    User user;
    @ManyToOne
    Task task;
}
