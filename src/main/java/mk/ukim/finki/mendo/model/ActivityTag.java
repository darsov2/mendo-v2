package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ActivityTag extends BaseEntity<Long> {
    String name;
    String color;
}
