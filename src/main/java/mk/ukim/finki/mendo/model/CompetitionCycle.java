package mk.ukim.finki.mendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToMany(mappedBy = "cycle", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cycle")
    private List<Competition> competitions;


    public CompetitionCycle(String name, LocalDate year, LocalDateTime registrationDeadline) {

        this.name = name;
        this.year = year;
        this.registrationDeadline = registrationDeadline;
    }
}
