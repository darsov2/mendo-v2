package mk.ukim.finki.mendo.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School extends BaseEntity<Long> {
    String name;
    String address;
}
