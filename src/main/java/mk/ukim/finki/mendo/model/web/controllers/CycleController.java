package mk.ukim.finki.mendo.model.web.controllers;

import mk.ukim.finki.mendo.model.CompetitionCycle;
import mk.ukim.finki.mendo.model.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.model.web.request.CompetitionCycleRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        model.addAttribute("bodyContent", "home");
        return "master";
    }
}
