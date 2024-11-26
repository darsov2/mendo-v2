package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
public class Participation extends BaseAuditedEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Competition competition;
}
