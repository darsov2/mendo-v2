package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findAllByCycleId(Long cycleId);
    List<Competition> findAllByCycleIsNullOrderByStartTimeAsc();
    List<Competition> findAllByCycleIdOrderByStartTimeAsc(Long cycleId);
    @Query("SELECT c FROM Competition c WHERE c.registrationOpens <= :currentDate AND c.registrationCloses >= :currentDate")
    List<Competition> findAllByRegistrationDateBetween(@Param("currentDate") LocalDateTime currentDate);
}
