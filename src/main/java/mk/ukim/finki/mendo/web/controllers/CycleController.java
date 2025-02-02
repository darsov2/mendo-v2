package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.request.CompetitionCycleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cycle")
public class CycleController {
    private final CompetitionCycleMapper cycleMapper;
    private final CompetitionCycleService cycleService;
    private final MendoUserService mendoUserService;

    public CycleController(CompetitionCycleMapper cycleMapper, CompetitionCycleService cycleService, MendoUserService mendoUserService) {
        this.cycleMapper = cycleMapper;
        this.cycleService = cycleService;
        this.mendoUserService = mendoUserService;
    }

    @GetMapping("/add")
    public String getCycle(Model model){

        model.addAttribute("bodyContent", "admin/addCompetitionCycle");
        return "master";
    }

    @PostMapping("/add")
    public String addCycle(CompetitionCycleRequest request, Model model){
        if (cycleMapper.addCompetitionCycle(request) == null){
            throw new RuntimeException("Can't add competitionCycle");
        }
        //todo the right path should be listing of competitions
        model.addAttribute("bodyContent", "competitions");
        return "master";
    }

    @GetMapping("/{id}")
    public String getCycleDetails(@PathVariable Long id, Model model) {
        CompetitionCycle cycle = cycleService.findById(id);
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        model.addAttribute("cycle", cycle);
        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("bodyContent", "cycle/cycle-details");
        return "master";
    }
}
