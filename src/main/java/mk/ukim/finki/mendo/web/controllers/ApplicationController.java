package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Application;
import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.ApplicationService;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.ParticipationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final MendoUserService mendoUserService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;

    public ApplicationController(ApplicationService applicationService, MendoUserService mendoUserService, ParticipationService participationService, CompetitionService competitionService) {
        this.applicationService = applicationService;
        this.mendoUserService = mendoUserService;
        this.participationService = participationService;
        this.competitionService = competitionService;
    }

    @PostMapping("/cycle/{cycleId}")
    @ResponseBody
    public Map<String, Object> registerForCompetitionCycle(
            @PathVariable Long cycleId,
            @RequestParam Grade grade,
            @RequestParam Long schoolId
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (applicationService.isUserAlreadyRegisteredOnCycle(cycleId)) {
                response.put("success", false);
                response.put("message", "Веќе сте регистрирани за овој натпревар.");
                return response;
            }

            applicationService.registerForCompetitionCycle(cycleId, grade, schoolId);
            response.put("success", true);
            response.put("message", "Успешно се пријавивте за натпреварот.");

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }
    @PostMapping("/competition/{competitionId}")
    @ResponseBody
    public Map<String, Object> registerForCompetition(
            @PathVariable Long competitionId,
            @RequestParam Grade grade,
            @RequestParam Long schoolId
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            MendoUser currentUser = mendoUserService.getCurrentUserOrThrow();
            if (applicationService.isUserAlreadyRegisteredOnCompetition(competitionId,currentUser.getUsername())) {
                response.put("success", false);
                response.put("message", "Веќе сте регистрирани за овој натпревар.");
                return response;
            }

            applicationService.registerForCompetition(competitionId, grade, schoolId);
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
        model.addAttribute("bodyContent","admin/application/applications");
        return "master";
    }

    @GetMapping("/admin/usernameApply")
    public String applyForStudentsWithUsername(Model model,
                                               @RequestParam(required = false) Map<String, String> results) {
        List<Competition> competitions = competitionService.findAll();

        model.addAttribute("competitions", competitions);
        model.addAttribute("results", results);
        model.addAttribute("bodyContent", "admin/application/usernameApply");

        return "master";
    }

    @PostMapping("/admin/usernameApply")
    public String studentUsernameApply(RedirectAttributes redirectAttributes,
                                       @RequestParam("competition") Long competitionId,
                                       @RequestParam("usernames") String usernames) {
        School school = new School("name", "add");
        MendoUser currentUser = new MendoUser(
                true, "teacher123", "John", "Doe", "password123",
                "Skopje", "Macedonia", null, "john.doe@school.edu",
                null, List.of(school), true, true, true, true
        );

        List<String> usernameList = Arrays.stream(usernames.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        Map<String, String> results = new HashMap<>();

        for (String student : usernameList) {
            Optional<MendoUser> user = mendoUserService.findByUsername(student);

            if (user.isEmpty()) {
                results.put(student, "Не постои регистраиран корисник со тоа корисничко име");
            } else if (applicationService.isUserAlreadyRegisteredOnCompetition(competitionId, student)) {
                results.put(student, "Ученикот е веќе регистриран за овој натпревар");
            } else {
                try {
                    Competition competition = competitionService.findById(competitionId);
                    Application application = new Application(LocalDate.now(), currentUser, user.get(), competition, true);
                    applicationService.save(application);

                    participationService.save(application.getStudent().getId(), application.getCompetition().getId());
                    results.put(student, "Ученикот е успешно регистриран");
                } catch (Exception e) {
                    results.put(student, e.getMessage());
                }
            }
        }

        results.forEach((key, value) -> redirectAttributes.addFlashAttribute("result_" + key, value));

        return "redirect:/admin/usernameApply";
    }

    @PostMapping("/admin/approve/{id}")
    public String approveApplication(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);

        application.setConfirmed(true);
        applicationService.save(application);

        participationService.save(application.getStudent().getId(), application.getCompetition().getId());

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