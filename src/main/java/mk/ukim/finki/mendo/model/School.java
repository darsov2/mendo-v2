package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class School extends BaseEntity<Long> {
    String name;
    String address;


    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
