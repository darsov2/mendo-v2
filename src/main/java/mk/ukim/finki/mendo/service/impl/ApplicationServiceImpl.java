package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.repository.ApplicationRepository;
import mk.ukim.finki.mendo.service.ApplicationService;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CompetitionCycleService competitionCycleService;
    private final SchoolService schoolService;
    private final MendoUserService mendoUserService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, CompetitionCycleService competitionCycleService, SchoolService schoolService, MendoUserService mendoUserService) {
        this.applicationRepository = applicationRepository;
        this.competitionCycleService = competitionCycleService;
        this.schoolService = schoolService;
        this.mendoUserService = mendoUserService;
    }

    @Override
    public boolean isUserAlreadyRegistered(Long cycleId) {
        MendoUser currentUser = mendoUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Not logged in!"));

        return applicationRepository.findByCompetitionCycle_IdAndStudent_Id(cycleId, currentUser.getId())
                .isPresent();
    }

    @Override
    @Transactional
    public Application registerForCompetition(Long cycleId, Grade grade, Long schoolId) {
        MendoUser currentUser = mendoUserService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("Не сте најавени!"));

        CompetitionCycle cycle = competitionCycleService.findById(cycleId);

        if (cycle.getRegistrationDeadline().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Рокот за пријавување е истечен за: " + cycle.getName());
        }

        School school = schoolService.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("Училиштето не е пронајдено!"));

        currentUser.setGrade(grade);
        currentUser.setStudiesSchool(school);
        mendoUserService.saveUser(currentUser);

        Application application = new Application(LocalDate.now(), null, currentUser, cycle,false);
        return applicationRepository.save(application);
    }


}