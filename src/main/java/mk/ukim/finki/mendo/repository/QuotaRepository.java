package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Quota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaRepository extends JpaRepository<Quota, Long> {
}
