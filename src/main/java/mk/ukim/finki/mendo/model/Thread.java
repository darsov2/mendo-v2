package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread extends BaseAuditedEntity<Long> {
    String name;
}
