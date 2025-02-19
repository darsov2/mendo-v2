package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final MendoUserService mendoUserService;
    private final ParticipationService participationService;
    private final CompetitionService competitionService;
    private final AuthorizationService authorizationService;


    public ApplicationController(ApplicationService applicationService, MendoUserService mendoUserService, ParticipationService participationService, CompetitionService competitionService, AuthorizationService authorizationService) {
        this.applicationService = applicationService;
        this.mendoUserService = mendoUserService;
        this.participationService = participationService;
        this.competitionService = competitionService;
        this.authorizationService = authorizationService;
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
            if (applicationService.isUserAlreadyRegisteredOnCompetition(competitionId, currentUser.getUsername())) {
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
        authorizationService.canManageApplications();

        MendoUser currentUser = mendoUserService.getCurrentUser().orElseThrow(RuntimeException::new);
//        School school = new School("name", "add");
//        school.setId((long) 1);
//        MendoUser currentUser = new MendoUser(
//                true,                   // isTeacher
//                "teacher123",           // username
//                "John",                 // name
//                "Doe",                  // surname
//                "password123",          // password
//                "Skopje",              // city
//                "Macedonia",           // country
//                null,                  // grade (null since it's a teacher)
//                "john.doe@school.edu", // email
//                null,                  // studiesSchool (null since it's a teacher)
//                List.of(school), // teachesSchools (school with id 1)
//                true,                  // isAccountNonExpired
//                true,                  // isAccountNonLocked
//                true,                  // isCredentialsNonExpired
//                true                   // isEnabled
//        );
//        if (!currentUser.getIsTeacher()) {
//            throw new RuntimeException("User is not teacher");
//        }

        List<School> schoolsWhereTeaches = currentUser.getTeachesSchools();

        List<Application> allApplications = applicationService.getAllApplicationsForTeacherSchoolsAndNotConfirmed(schoolsWhereTeaches);


        model.addAttribute("applications", allApplications);
        model.addAttribute("bodyContent", "admin/application/applications");
        return "master";
    }

    @GetMapping("/admin/usernameApply")
    public String applyForStudentsWithUsername(Model model,
                                               @ModelAttribute("results") Map<String, String> results) {
        authorizationService.canManageApplications();

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
        authorizationService.canManageApplications();

        MendoUser currentUser = mendoUserService.getCurrentUser().orElseThrow(RuntimeException::new);
//        School school = new School("name", "add");
//        school.setId((long) 1);
//        MendoUser currentUser = new MendoUser(
//                true,                   // isTeacher
//                "teacher123",           // username
//                "John",                 // name
//                "Doe",                  // surname
//                "password123",          // password
//                "Skopje",              // city
//                "Macedonia",           // country
//                null,                  // grade (null since it's a teacher)
//                "john.doe@school.edu", // email
//                null,                  // studiesSchool (null since it's a teacher)
//                List.of(school), // teachesSchools (school with id 1)
//                true,                  // isAccountNonExpired
//                true,                  // isAccountNonLocked
//                true,                  // isCredentialsNonExpired
//                true                   // isEnabled
//        );
//        if (!currentUser.getIsTeacher()) {
//            throw new RuntimeException("User is not teacher");
//        }
        currentUser = mendoUserService.saveUser(currentUser);

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

        redirectAttributes.addFlashAttribute("results", results);

        return "redirect:/application/admin/usernameApply";
    }

    @PostMapping("/admin/approve/{id}")
    public String approveApplication(@PathVariable Long id) {
        authorizationService.canManageApplications();

        Application application = applicationService.getApplicationById(id);

        application.setConfirmed(true);
        applicationService.save(application);

        participationService.save(application.getStudent().getId(), application.getCompetition().getId());

        return "redirect:/application/admin/view";
    }


    @PostMapping("/admin/refuse/{id}")
    public String refuseApplication(@PathVariable Long id) {
        authorizationService.canManageApplications();

        Application application = applicationService.getApplicationById(id);

        application.setConfirmed(true);
        applicationService.save(application);

        return "redirect:/application/admin/view";
    }

    @GetMapping("allApplications")
    public String allApplications(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Long competitionId,
            @RequestParam(required = false) Boolean confirmationStatus,
            Model model) {
        authorizationService.canViewApplications();

        MendoUser currentUser = mendoUserService.getCurrentUser().orElseThrow(RuntimeException::new);

        // Get all applications the user can see
        List<Application> allApplications = applicationService.getAllApplicationsWhereMentorTeaches(currentUser.getId());
//        List<Application> allApplications = applicationService.getAllApplications();
        // Apply filters if provided
        List<Application> filteredApplications = allApplications;

        // Filter by search term
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            String search = searchTerm.toLowerCase();
            filteredApplications = filteredApplications.stream()
                    .filter(app ->
                            app.getStudent().getFullName().toLowerCase().contains(search) ||
                                    app.getStudent().getStudiesSchool().getName().toLowerCase().contains(search) ||
                                    app.getStudent().getEmail().toLowerCase().contains(search))
                    .collect(Collectors.toList());
        }

        // Filter by competition
        if (competitionId != null) {
            filteredApplications = filteredApplications.stream()
                    .filter(app -> app.getCompetition().getId().equals(competitionId))
                    .collect(Collectors.toList());
        }

        // Filter by confirmation status
        if (confirmationStatus != null) {
            filteredApplications = filteredApplications.stream()
                    .filter(app -> app.getConfirmed() == confirmationStatus)
                    .collect(Collectors.toList());
        }

        // Calculate statistics based on filtered applications
        long confirmedCount = filteredApplications.stream()
                .filter(Application::getConfirmed)
                .count();

        long unconfirmedCount = filteredApplications.size() - confirmedCount;

        long uniqueSchoolsCount = filteredApplications.stream()
                .map(a -> a.getStudent().getStudiesSchool().getId())
                .distinct()
                .count();

        // Get all competitions for the dropdown
        List<Competition> competitions = competitionService.findAll();

        // Add all attributes to the model
        model.addAttribute("applications", filteredApplications);
        model.addAttribute("competitions", competitions);
        model.addAttribute("confirmedCount", confirmedCount);
        model.addAttribute("unconfirmedCount", unconfirmedCount);
        model.addAttribute("uniqueSchoolsCount", uniqueSchoolsCount);

        // Add filter params to maintain state
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("competitionId", competitionId);
        model.addAttribute("confirmationStatus", confirmationStatus);

        model.addAttribute("bodyContent", "admin/application/allApplications");

        return "master";
    }
}