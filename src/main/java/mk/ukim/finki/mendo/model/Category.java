package mk.ukim.finki.mendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"parentCategory", "children"})
public class Category extends BaseEntity<Long> {
    private String name;
    @ManyToOne
    @ToString.Exclude
    private Category parentCategory;
    @ToString.Exclude
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;
}
