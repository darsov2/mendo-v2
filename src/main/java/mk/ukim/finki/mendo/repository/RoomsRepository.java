package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    List<Rooms> findAllByCity(String city);
}
