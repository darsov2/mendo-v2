package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/competitions")
public class CompetitionsController {

    private final CompetitionService competitionService;
    private final CompetitionCycleService competitionCycleService;
    private final SchoolService schoolService;
    private final MendoUserService mendoUserService;

    public CompetitionsController(CompetitionService competitionService,
                                  CompetitionCycleService competitionCycleService, SchoolService schoolService, MendoUserService mendoUserService) {
        this.competitionService = competitionService;
        this.competitionCycleService = competitionCycleService;
        this.schoolService = schoolService;
        this.mendoUserService = mendoUserService;
    }

    @GetMapping
    public String home(Model model) {
        List<School> schools = schoolService.findAll();
        List<CompetitionCycle> cycles = competitionCycleService.findAllSortedByYearDesc();
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        Map<Long, List<Competition>> competitionsByCycle = cycles.stream()
                .collect(Collectors.toMap(
                        CompetitionCycle::getId,
                        cycle -> competitionService.findAllByCycleId(cycle.getId())
                ));

        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute("competitionCycles", cycles);
        model.addAttribute("competitionsByCycle", competitionsByCycle);
        model.addAttribute("bodyContent", "competitions");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("selectedCycle", new CompetitionCycle());
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());
        return "master";
    }

    @GetMapping("/{id}")
    public String getCompetitionDetails(@PathVariable Long id, Model model) {
        return competitionService.findById(id)
                .map(competition -> {
                    model.addAttribute("competition", competition);
                    return "competition-details";
                })
                .orElse("redirect:/competitions?error=Competition+not+found");
    }
}