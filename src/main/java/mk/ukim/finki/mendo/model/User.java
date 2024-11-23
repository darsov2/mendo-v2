package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Boolean isTeacher;
    String username;
    String name;
    String surname;
    String password;
    String city; //?
    String country; //?
    Double grade; //todo
    String email;
    @ManyToOne
    School studiesSchool;
    @ManyToMany
    @JoinTable(name = "schools_teaches",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "school_id"))
    List<School> teachesSchools;
}
