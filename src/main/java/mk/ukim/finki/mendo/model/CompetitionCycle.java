package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.web.controllers.BaseAuditedEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class CompetitionCycle extends BaseAuditedEntity<Long> {
    private String name;
    private LocalDate year;
    private LocalDateTime registrationDeadline;


    public CompetitionCycle(String name, LocalDate year, LocalDateTime registrationDeadline) {
        this.name = name;
        this.year = year;
        this.registrationDeadline = registrationDeadline;
    }
}