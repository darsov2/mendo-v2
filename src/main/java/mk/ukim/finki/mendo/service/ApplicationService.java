package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Application;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.enums.Grade;

import java.util.List;

public interface ApplicationService {
    Application registerForCompetitionCycle(Long cycleId, Grade grade, Long schoolId);
    Application registerForCompetition(Long competitionId, Grade grade, Long schoolId);
    public boolean isUserAlreadyRegisteredOnCycle(Long cycleId);
    List<Application> getAllApplicationsBySchoolId(Long schoolId);
    public List<Application> getAllApplicationsForTeacherSchoolsAndNotConfirmed(List<School> teacherSchools);
    public Application getApplicationById(Long applicationId);
    public Application save(Application application);
    public boolean isUserAlreadyRegisteredOnCompetition(Long competitionId, String username);
    public List<Application> getAllApplicationsWhereMentorTeaches(Long teacherId);
    public List<Application> getAllApplications();
}