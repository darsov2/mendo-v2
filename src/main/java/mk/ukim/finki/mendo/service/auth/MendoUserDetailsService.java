package mk.ukim.finki.mendo.service.auth;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.repository.MendoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MendoUserDetailsService implements UserDetailsService {
    private final MendoUserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(MendoUserDetailsService.class);

    public MendoUserDetailsService(MendoUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MendoUser mendoUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        logger.info("Loaded user: {}", mendoUser.getUsername());
        return new User(username, mendoUser.getPassword(), mendoUser.getAuthorities());
    }
}
