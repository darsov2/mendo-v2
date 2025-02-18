package mk.ukim.finki.mendo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionTask extends BaseAuditedEntity<Long> {
    Integer points;
    @Enumerated(EnumType.STRING)
    CompetitionTypes types;
    @Column(columnDefinition = "TEXT")
    String description;
    @ManyToOne
    @JsonBackReference("competition-tasks")
    private Competition competition;
    @ManyToOne
    Task task;
}
