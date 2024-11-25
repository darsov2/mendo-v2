package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.model.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
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
