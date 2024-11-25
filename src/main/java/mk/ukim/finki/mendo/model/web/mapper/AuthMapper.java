package mk.ukim.finki.mendo.model.web.mapper;

import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.web.request.UserRegisterRequest;
import mk.ukim.finki.mendo.service.MendoUserService;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {
    private final MendoUserService userService;

    public AuthMapper(MendoUserService userService) {
        this.userService = userService;
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

        return userService.registerUser(mendoUser) != null;
    }
}
