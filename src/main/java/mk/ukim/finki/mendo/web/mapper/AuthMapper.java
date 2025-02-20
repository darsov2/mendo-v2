package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.enums.RoleNames;
import mk.ukim.finki.mendo.service.RoleService;
import mk.ukim.finki.mendo.web.request.UserRegisterRequest;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthMapper {
    private final MendoUserService userService;
    private final SchoolMapper schoolMapper;
    private final RoleService roleService;

    public AuthMapper(MendoUserService userService, SchoolMapper schoolMapper, RoleService roleService) {
        this.userService = userService;
        this.schoolMapper = schoolMapper;
        this.roleService = roleService;
    }

    public Boolean registerUser(UserRegisterRequest userRegisterRequest) {
        MendoUser mendoUser = new MendoUser();
        mendoUser.setEmail(userRegisterRequest.getEmail());
        mendoUser.setUsername(userRegisterRequest.getUsername());
        mendoUser.setPassword(userRegisterRequest.getPassword());
        mendoUser.setName(userRegisterRequest.getFirstName());
        mendoUser.setSurname(userRegisterRequest.getLastName());
        mendoUser.setGrade(userRegisterRequest.getGrade());
        mendoUser.setCity(userRegisterRequest.getCity());
        mendoUser.setCountry(userRegisterRequest.getCountry());
        if(userRegisterRequest.getSchoolId() != null) {
            mendoUser.setStudiesSchool(schoolMapper.findById(userRegisterRequest.getSchoolId()));
        }
        mendoUser.setRoles(Set.of(roleService.findRoleByName(RoleNames.ROLE_STUDENT)));
        return userService.registerUser(mendoUser) != null;
    }
}
