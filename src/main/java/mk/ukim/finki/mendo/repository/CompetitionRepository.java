package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findAllByCycleId(Long cycleId);
    List<Competition> findAllByCycleIsNullOrderByStartTimeAsc();
    List<Competition> findAllByCycleIdOrderByStartTimeAsc(Long cycleId);
}
