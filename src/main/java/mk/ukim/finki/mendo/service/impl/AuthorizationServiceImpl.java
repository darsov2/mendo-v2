package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final MendoUserService userService;

    public AuthorizationServiceImpl(MendoUserService userService) {
        this.userService = userService;
    }

    @Override
    public void canViewSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("VIEW_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to view schools");
        }
    }

    @Override
    public void canEditSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("EDIT_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to edit schools");
        }
    }

    @Override
    public void canCreateSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("CREATE_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to create schools");
        }
    }

    @Override
    public void canDeleteSchools() {
        if (userService.getCurrentUser().isEmpty() ||
                !userService.getCurrentUser().get()
                        .getAuthorities().contains(new SimpleGrantedAuthority("DELETE_SCHOOL"))) {
            throw new AccessDeniedException("User is not authorized to delete schools");
        }
    }
}
