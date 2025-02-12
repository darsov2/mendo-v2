package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.ContentType;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Content extends BaseAuditedEntity<Long> {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;
    private String title;
    @Column(columnDefinition = "TEXT")
    String text;

    @ManyToMany
    List<ActivityTag> tags;

    @ManyToOne
    Category category;

    Boolean visible = true;

    @Column(name = "content_order")
    Integer order;
    String source;
    @ManyToOne
    Topic topic;
}
