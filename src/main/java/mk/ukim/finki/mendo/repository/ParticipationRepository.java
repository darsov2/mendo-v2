package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation,Long> {
    List<Participation> findAllByCompetition_Id(Long competitionId);
}
