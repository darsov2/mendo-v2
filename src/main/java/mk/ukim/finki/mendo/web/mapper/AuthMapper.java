package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.web.request.UserRegisterRequest;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {
    private final MendoUserService userService;
    private final SchoolMapper schoolMapper;

    public AuthMapper(MendoUserService userService, SchoolMapper schoolMapper) {
        this.userService = userService;
        this.schoolMapper = schoolMapper;
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
        mendoUser.setStudiesSchool(schoolMapper.findById(userRegisterRequest.getSchoolId()));

        return userService.registerUser(mendoUser) != null;
    }
}
