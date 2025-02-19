package mk.ukim.finki.mendo.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.repository.ApplicationRepository;
import mk.ukim.finki.mendo.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CompetitionCycleService competitionCycleService;
    private final CompetitionService competitionService;
    private final SchoolService schoolService;
    private final MendoUserService mendoUserService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, CompetitionCycleService competitionCycleService, CompetitionService competitionService, SchoolService schoolService, MendoUserService mendoUserService) {
        this.applicationRepository = applicationRepository;
        this.competitionCycleService = competitionCycleService;
        this.competitionService = competitionService;
        this.schoolService = schoolService;
        this.mendoUserService = mendoUserService;
    }

    @Override
    public boolean isUserAlreadyRegisteredOnCycle(Long cycleId) {
        MendoUser currentUser = mendoUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Not logged in!"));

        return applicationRepository.findByCompetition_Cycle_IdAndStudent_Id(cycleId, currentUser.getId())
                .isPresent();
    }

    @Override
    public List<Application> getAllApplicationsBySchoolId(Long schoolId) {
        return this.applicationRepository.findByStudent_StudiesSchool_Id(schoolId);
    }

    public List<Application> getAllApplicationsForTeacherSchoolsAndNotConfirmed(List<School> teacherSchools) {
        List<Long> schoolIds = teacherSchools.stream()
                .map(School::getId)
                .collect(Collectors.toList());
        return applicationRepository.findByStudent_StudiesSchool_IdInAndConfirmedFalse(schoolIds);
    }

    @Override
    public Application getApplicationById(Long applicationId) {
        return applicationRepository.findById(applicationId).get();
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public boolean isUserAlreadyRegisteredOnCompetition(Long competitionId, String username) {
        MendoUser currentUser = mendoUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Not logged in!"));
        return applicationRepository.findByStudent_UsernameAndCompetition_Id(username,competitionId) != null;
    }

    @Override
    public List<Application> getAllApplicationsWhereMentorTeaches(Long teacherId) {
        return applicationRepository.findApplicationsWhereStudentsStudyAtMentorTeachingSchools(teacherId);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    @Transactional
    public Application registerForCompetitionCycle(Long cycleId, Grade grade, Long schoolId) {
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        if (currentUser == null) {
            throw new RuntimeException("Not logged in!");
        }

        CompetitionCycle cycle = competitionCycleService.findById(cycleId);

        if (cycle.getRegistrationDeadline().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Registration ended for: "+cycle.getName());
        }

        School school = schoolService.findById(schoolId);

        currentUser.setGrade(grade);
        currentUser.setStudiesSchool(school);
        mendoUserService.saveUser(currentUser);

        Application application = new Application(LocalDate.now(), null, currentUser, cycle.getCompetitions().getFirst(),false);
        return applicationRepository.save(application);
    }

    @Override
    public Application registerForCompetition(Long competitionId, Grade grade, Long schoolId) {
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        if (currentUser == null) {
            throw new RuntimeException("Not logged in!");
        }

        Competition competition = competitionService.findById(competitionId);

        if (competition.getRegistrationOpens().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Registration ended for: "+competition.getTitle());
        }

        School school = schoolService.findById(schoolId);

        currentUser.setGrade(grade);
        currentUser.setStudiesSchool(school);
        mendoUserService.saveUser(currentUser);

        Application application = new Application(LocalDate.now(), null, currentUser, competition,false);
        return applicationRepository.save(application);
    }


}