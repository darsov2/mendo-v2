package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.request.CompetitionCycleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cycle")
public class CycleController {
    private final CompetitionCycleMapper cycleMapper;
    private final CompetitionCycleService cycleService;

    public CycleController(CompetitionCycleMapper cycleMapper, CompetitionCycleService cycleService) {
        this.cycleMapper = cycleMapper;
        this.cycleService = cycleService;
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
        model.addAttribute("cycle", cycle);
        model.addAttribute("bodyContent", "cycle/cycle_details");
        return "master";
    }
}
