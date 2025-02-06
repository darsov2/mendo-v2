package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.Rank;
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
    Double finalPoints = 0.0;

    @Enumerated(EnumType.STRING)
    Rank rank;

    public Participation(MendoUser mendoUser, Competition competition, Rooms room, Double finalPoints) {
        this.mendoUser = mendoUser;
        this.competition = competition;
        this.room = room;
        this.finalPoints = finalPoints;
    }
}
