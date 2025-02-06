package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.MendoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MendoUserRepository extends JpaRepository<MendoUser, Long> {
    Optional<MendoUser> findByUsername(String username);

    List<MendoUser> findAllByIdIn(List<Long> list);
}
