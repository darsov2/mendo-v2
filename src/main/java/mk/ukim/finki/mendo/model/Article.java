package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;
import org.hibernate.annotations.Type;

@Entity
@Data
@NoArgsConstructor
public class Article extends BaseAuditedEntity<Long> {
    String text;
}
