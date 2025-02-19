package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
