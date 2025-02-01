package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.ContentType;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Content extends BaseAuditedEntity<Long> {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    @ManyToMany
    List<ActivityTag> tags;

    @ManyToOne
    Category category;

    Boolean visible = true;

    Integer order;
}
