package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.model.web.controllers.BaseAuditedEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Competition extends BaseAuditedEntity<Long> {
    String title;
    LocalDate startDate;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    CompetitionTypes type;
    String place;
    String info;
    LocalDateTime deadline;
}
