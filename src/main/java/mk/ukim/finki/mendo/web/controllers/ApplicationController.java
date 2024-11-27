package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Application;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.ApplicationService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.ParticipationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final MendoUserService mendoUserService;
    private final ParticipationService participationService;

    public ApplicationController(ApplicationService applicationService, MendoUserService mendoUserService, ParticipationService participationService) {
        this.applicationService = applicationService;
        this.mendoUserService = mendoUserService;
        this.participationService = participationService;
    }

    @PostMapping("/{cycleId}")
    @ResponseBody
    public Map<String, Object> registerForCompetition(
            @PathVariable Long cycleId,
            @RequestParam Grade grade,
            @RequestParam Long schoolId
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (applicationService.isUserAlreadyRegistered(cycleId)) {
                response.put("success", false);
                response.put("message", "Веќе сте регистрирани за овој натпревар.");
                return response;
            }

            applicationService.registerForCompetition(cycleId, grade, schoolId);
            response.put("success", true);
            response.put("message", "Успешно се пријавивте за натпреварот.");

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/admin/view")
    public String getAllApplicationsForSchool(Model model) {
//        MendoUser currentUser = mendoUserService.getCurrentUser().orElseThrow(RuntimeException::new);
        School school = new School("name","add");
        school.setId((long)1);
        MendoUser currentUser = new MendoUser(
                true,                   // isTeacher
                "teacher123",           // username
                "John",                 // name
                "Doe",                  // surname
                "password123",          // password
                "Skopje",              // city
                "Macedonia",           // country
                null,                  // grade (null since it's a teacher)
                "john.doe@school.edu", // email
                null,                  // studiesSchool (null since it's a teacher)
                List.of(school), // teachesSchools (school with id 1)
                true,                  // isAccountNonExpired
                true,                  // isAccountNonLocked
                true,                  // isCredentialsNonExpired
                true                   // isEnabled
        );
        if (!currentUser.getIsTeacher()) {
            throw new RuntimeException("User is not teacher");
        }

        List<School> schoolsWhereTeaches = currentUser.getTeachesSchools();

        List<Application> allApplications = applicationService.getAllApplicationsForTeacherSchoolsAndNotConfirmed(schoolsWhereTeaches);


        model.addAttribute("applications", allApplications);
        model.addAttribute("bodyContent","admin/applications");
        return "master";
    }

    @PostMapping("/admin/approve/{id}")
    public String approveApplication(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);

        application.setConfirmed(true);
        applicationService.save(application);

        participationService.save(application.getStudent().getId(), application.getCompetitionCycle().getId());

        return "redirect:/application/admin/view";
    }

    @PostMapping("/admin/refuse/{id}")
    public String refuseApplication(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);

        application.setConfirmed(true);
        applicationService.save(application);

        return "redirect:/application/admin/view";
    }
}