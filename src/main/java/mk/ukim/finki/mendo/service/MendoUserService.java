package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.MendoUser;

import java.util.List;
import java.util.Optional;

public interface MendoUserService {
    MendoUser registerUser(MendoUser user);
    Optional<MendoUser> getCurrentUser();
    MendoUser getCurrentUserOrThrow();
    MendoUser saveUser(MendoUser user);
    MendoUser findById(Long id);
    Optional<MendoUser> findByUsername(String username);

    List<MendoUser> findAllByIdIn(List<Long> list);
}