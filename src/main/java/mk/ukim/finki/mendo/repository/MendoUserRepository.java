package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MendoUserRepository extends JpaRepository<MendoUser, Long> {
    Optional<MendoUser> findByUsername(String username);

    List<MendoUser> findAllByIdIn(List<Long> list);
    @Query("SELECT m from MendoUser m join m.roles r where r.name = :role")
    List<MendoUser> findAllByRole(@Param("role") RoleNames role);

}

