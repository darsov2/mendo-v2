package mk.ukim.finki.mendo.service;

public interface AuthorizationService {
    void canViewSchools();
    void canEditSchools();
    void canCreateSchools();
    void canDeleteSchools();

}
