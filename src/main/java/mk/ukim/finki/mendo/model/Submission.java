package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.SubmissionResult;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Data
@NoArgsConstructor
@Entity
public class Submission extends BaseAuditedEntity<Long> {
    String language;
    @Enumerated(EnumType.STRING)
    SubmissionResult submissionResult;
    //todo Code
    @ManyToOne
    MendoUser mendoUser;
    @ManyToOne
    Task task;
}
