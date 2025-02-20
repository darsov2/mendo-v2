package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Role;
import mk.ukim.finki.mendo.model.enums.RoleNames;

public interface RoleService {
    Role findRoleByName(RoleNames role);
}
