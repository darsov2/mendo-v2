package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

@Entity
@Data
@NoArgsConstructor
public class CompetitionTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer points;
    @Enumerated(EnumType.STRING)
    CompetitionTypes types;
    String description;
    @ManyToOne
    Competition competition;
    @ManyToOne
    Task task;

}
