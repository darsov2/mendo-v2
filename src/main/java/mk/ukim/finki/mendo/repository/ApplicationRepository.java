package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByCompetitionCycle_IdAndStudent_Id(Long cycleId, Long userId);
}
