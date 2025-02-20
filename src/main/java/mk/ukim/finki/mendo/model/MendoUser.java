package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.Grade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class MendoUser extends BaseAuditedEntity<Long> implements UserDetails {
    Boolean isTeacher;
    String username;
    String name;
    String surname;
    String password;
    String city; //?
    String country; //?
    Grade grade; //todo
    String email;
    @ManyToOne
    School studiesSchool;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "schools_teaches",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "school_id"))
    List<School> teachesSchools = new ArrayList<>();
    Boolean isAccountNonExpired = true;
    Boolean isAccountNonLocked = true;
    Boolean isCredentialsNonExpired = true;
    Boolean isEnabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public MendoUser(boolean isTeacher, String username, String name, String surname, String password, String city, String country, Grade grade, String email, School studiesSchool, List<School> teachesSchools, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.isTeacher = isTeacher;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
        this.country = country;
        this.grade = grade;
        this.email = email;
        this.studiesSchool = studiesSchool;
        this.teachesSchools = teachesSchools;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));

            role.getPermissions().forEach(permission ->
                    authorities.add(new SimpleGrantedAuthority(permission.getName()))
            );
        });

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getFullName(){
        return name + " " + surname;
    }
}
