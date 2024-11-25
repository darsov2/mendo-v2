package mk.ukim.finki.mendo;

import jakarta.persistence.Entity;
import lombok.Data;
import mk.ukim.finki.mendo.model.BaseEntity;

@Data
@Entity
public class ActivityTag extends BaseEntity<Long> {
    String name;
    String color;
}
