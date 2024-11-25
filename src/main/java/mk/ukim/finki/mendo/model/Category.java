package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Category extends BaseEntity<Long> {
    String name;
    @ManyToOne
    Category parentCategory;
}
