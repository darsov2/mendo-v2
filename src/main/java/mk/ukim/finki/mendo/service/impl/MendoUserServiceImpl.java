package mk.ukim.finki.mendo.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.repository.MendoUserRepository;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MendoUserServiceImpl implements MendoUserService {
    private final MendoUserRepository mendoUserRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(MendoUserServiceImpl.class);

    @Override
    @Transactional
    public MendoUser registerUser(MendoUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mendoUserRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MendoUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("No authentication found in SecurityContext");
            return Optional.empty();
        }

        if (!authentication.isAuthenticated()) {
            logger.debug("Authentication is not authenticated");
            return Optional.empty();
        }

        String username = extractUsername(authentication);
        if (username == null || username.equals("anonymousUser")) {
            logger.debug("Anonymous or null user detected");
            return Optional.empty();
        }

        logger.debug("Looking up user with username: {}", username);
        return mendoUserRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public MendoUser getCurrentUserOrThrow() {
        return getCurrentUser()
                .orElseThrow(() -> new RuntimeException("No authenticated user found"));
    }

    @Override
    public MendoUser saveUser(MendoUser user) {
        return mendoUserRepository.save(user);
    }

    @Override
    public MendoUser findById(Long id) {
        return mendoUserRepository.findById(id).get();
    }

    @Override
    public Optional<MendoUser> findByUsername(String username) {
        return mendoUserRepository.findByUsername(username);
    }

    @Transactional
    public String extractUsername(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            return (String) principal;
        }

        logger.warn("Unexpected principal type: {}", principal.getClass().getName());
        return null;
    }
}