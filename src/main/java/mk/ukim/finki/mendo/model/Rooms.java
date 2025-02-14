package mk.ukim.finki.mendo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Rooms extends BaseEntity<Long> {

    Integer capacity;
    String name;
    String city;
    @ManyToMany(mappedBy = "rooms")
    List<Competition> competitions;

    public Rooms(Integer capacity, String name, String city) {
        this.capacity = capacity;
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "capacity=" + capacity +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
