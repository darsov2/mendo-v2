package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.web.controllers.BaseAuditedEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToMany
    @JoinTable(name = "schools_teaches",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "school_id"))
    List<School> teachesSchools;
    Boolean isAccountNonExpired = true;
    Boolean isAccountNonLocked = true;
    Boolean isCredentialsNonExpired = true;
    Boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
