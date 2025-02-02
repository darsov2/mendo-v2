package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
public class Quota extends BaseAuditedEntity<Long> {
    @Column(name = "quota_limit")
    Integer limit;
    @ManyToOne
    School school;
    @ManyToOne
    Competition competition;

    public Quota(Integer limit, School school, Competition competition) {
        this.limit = limit;
        this.school = school;
        this.competition = competition;
    }
}
