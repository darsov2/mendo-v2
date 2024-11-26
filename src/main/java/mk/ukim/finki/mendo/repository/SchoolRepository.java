package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
