package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@NoArgsConstructor
@Data
public class Question extends BaseAuditedEntity<Long> {
    String description;
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Question question;
}
