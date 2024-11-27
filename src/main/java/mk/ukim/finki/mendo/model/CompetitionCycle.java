package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class CompetitionCycle extends BaseAuditedEntity<Long> {
    private String name;
    private LocalDate year;
    private LocalDateTime registrationDeadline;
    @OneToMany
    private List<Competition> competitions;


    public CompetitionCycle(String name, LocalDate year, LocalDateTime registrationDeadline) {
        this.name = name;
        this.year = year;
        this.registrationDeadline = registrationDeadline;
    }

}
