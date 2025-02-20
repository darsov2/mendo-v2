package mk.ukim.finki.mendo.web.request;

import lombok.Data;
import mk.ukim.finki.mendo.model.enums.Grade;

import java.util.List;

@Data
public class UserRegisterRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
    String username;
    String city;
    String country;
    Grade grade;
    Long schoolId;
    List<Long> roles;
}
