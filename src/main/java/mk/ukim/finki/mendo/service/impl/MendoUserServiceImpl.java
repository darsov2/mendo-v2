package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.repository.MendoUserRepository;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MendoUserServiceImpl implements MendoUserService {
    private final MendoUserRepository mendoUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MendoUserServiceImpl(MendoUserRepository mendoUserRepository, PasswordEncoder passwordEncoder) {
        this.mendoUserRepository = mendoUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MendoUser registerUser(MendoUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mendoUserRepository.save(user);
    }
}
