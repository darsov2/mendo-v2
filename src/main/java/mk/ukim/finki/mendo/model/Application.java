package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application extends BaseAuditedEntity<Long> {
    LocalDate date;

    @ManyToOne
    MendoUser mentor;

    @ManyToOne
    MendoUser student;

    @ManyToOne
    Competition competition;

    Boolean confirmed=false;
}
