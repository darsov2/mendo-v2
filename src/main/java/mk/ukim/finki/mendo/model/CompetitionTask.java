package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionTask extends BaseAuditedEntity<Long> {
    Integer points;
    @Enumerated(EnumType.STRING)
    CompetitionTypes types;
    String description;
    @ManyToOne
    Competition competition;
    @ManyToOne
    Task task;
}
