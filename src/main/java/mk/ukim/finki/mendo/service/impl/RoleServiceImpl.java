package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Role;
import mk.ukim.finki.mendo.model.enums.RoleNames;
import mk.ukim.finki.mendo.repository.RoleRepository;
import mk.ukim.finki.mendo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(RoleNames role) {
        return roleRepository.findByName(role);
    }
}
