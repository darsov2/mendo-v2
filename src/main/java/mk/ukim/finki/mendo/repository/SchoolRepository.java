package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAllByIdIn(List<Long> ids);
}
