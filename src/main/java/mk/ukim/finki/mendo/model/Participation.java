package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation extends BaseAuditedEntity<Long> {
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Competition competition;
    @ManyToOne
    Rooms room;
}
