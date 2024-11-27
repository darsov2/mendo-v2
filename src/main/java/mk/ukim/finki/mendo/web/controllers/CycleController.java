package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.request.CompetitionCycleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cycle")
public class CycleController {
    private final CompetitionCycleMapper cycleMapper;

    public CycleController(CompetitionCycleMapper cycleMapper) {
        this.cycleMapper = cycleMapper;
    }

    @GetMapping("")
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
}
