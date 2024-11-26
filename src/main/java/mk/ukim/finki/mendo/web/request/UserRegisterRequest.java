package mk.ukim.finki.mendo.web.request;

import lombok.Data;
import mk.ukim.finki.mendo.model.enums.Grade;

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
}
