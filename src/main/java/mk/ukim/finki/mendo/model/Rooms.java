package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

@Entity
@Data
@NoArgsConstructor
public class Rooms extends BaseEntity<Long> {

    Integer capacity;
    String name;
    String city;

    public Rooms(Integer capacity, String name, String city) {
        this.capacity = capacity;
        this.name = name;
        this.city = city;
    }
}
