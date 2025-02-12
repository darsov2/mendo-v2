package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Certificate extends BaseAuditedEntity<Long> {
    LocalDate date;
    String place;
    @OneToOne
    Participation participation;
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Competition competition;
}
