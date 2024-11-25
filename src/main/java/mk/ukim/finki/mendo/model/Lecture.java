package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.web.controllers.BaseAuditedEntity;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Lecture extends BaseAuditedEntity<Long> {
    String title;
    String text;
    @ManyToMany
    List<ActivityTag> tags;
    @ManyToOne
    Category category;
}
