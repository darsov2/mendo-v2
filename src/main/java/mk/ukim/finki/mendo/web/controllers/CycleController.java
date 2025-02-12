package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.request.CompetitionCycleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cycle")
public class CycleController {
    private final CompetitionCycleMapper cycleMapper;
    private final CompetitionCycleService cycleService;
    private final MendoUserService mendoUserService;
    private final SchoolService schoolService;
    private final AuthorizationService authorizationService;

    public CycleController(CompetitionCycleMapper cycleMapper, CompetitionCycleService cycleService, MendoUserService mendoUserService, SchoolService schoolService, AuthorizationService authorizationService) {
        this.cycleMapper = cycleMapper;
        this.cycleService = cycleService;
        this.mendoUserService = mendoUserService;
        this.schoolService = schoolService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/add")
    public String getCycle(Model model){
        authorizationService.canManageCompetitionCycles();

        model.addAttribute("bodyContent", "admin/addCompetitionCycle");
        return "master";
    }

    @PostMapping("/add")
    public String addCycle(CompetitionCycleRequest request, Model model){
        authorizationService.canManageCompetitionCycles();

        if (cycleMapper.addCompetitionCycle(request) == null){
            throw new RuntimeException("Can't add competitionCycle");
        }
        //todo the right path should be listing of competitions
        model.addAttribute("bodyContent", "competitions");
        return "master";
    }

    @GetMapping("/{id}")
    public String getCycleDetails(@PathVariable Long id, Model model) {
        authorizationService.canViewCompetitionCycles();

        CompetitionCycle cycle = cycleService.findById(id);
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;
        List<School> schools = schoolService.findAll();

        model.addAttribute("cycle", cycle);
        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("bodyContent", "cycle/cycle-details");
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());

        return "master";
    }
}
