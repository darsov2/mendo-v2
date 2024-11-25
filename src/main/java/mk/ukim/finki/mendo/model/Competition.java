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

    @ManyToOne
    CompetitionCycle cycle;


    public Competition(String title, LocalDate startDate, LocalDateTime startTime, LocalDateTime endTime, CompetitionTypes type, String place, String info, LocalDateTime deadline, CompetitionCycle cycle) {
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.place = place;
        this.info = info;
        this.deadline = deadline;
        this.cycle = cycle;
    }
}
