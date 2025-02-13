package mk.ukim.finki.mendo.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mk.ukim.finki.mendo.model.BaseEntity;
import mk.ukim.finki.mendo.model.Category;

import java.util.List;

@Data
public class CategoryRequest {

    String name;

    Long parentCategoryId;

    List<Long> childrenId;

}
