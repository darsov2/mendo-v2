package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.web.controllers.BaseAuditedEntity;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Application extends BaseAuditedEntity<Long> {
    LocalDate date;
    @ManyToOne
    MendoUser mentor;
    @ManyToOne
    MendoUser student;
    @ManyToOne
    Competition competition;
}
