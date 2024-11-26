package mk.ukim.finki.mendo.model.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.model.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.model.web.request.CompetitionCycleRequest;
import mk.ukim.finki.mendo.model.web.request.CompetitionRequest;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import mk.ukim.finki.mendo.service.CompetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

    private final CompetitionCycleService competitionCycleService;
    private final CompetitionMapper competitionMapper;
    public CompetitionController(CompetitionCycleService competitionCycleService, CompetitionMapper competitionMapper) {
        this.competitionCycleService = competitionCycleService;
        this.competitionMapper = competitionMapper;
    }

    @GetMapping("/add")
    public String getCompetition(Model model) {
        model.addAttribute("cycles", competitionCycleService.findAll());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("bodyContent", "admin/addCompetition" );
        return "master";
    }

    @PostMapping("/add")
    public String addCompetition(CompetitionRequest request, Model model){

        if (competitionMapper.addCompetition(request) == null){
            throw new RuntimeException("Can't add competition");
        }

        //todo the right path should be listing of competitions
        model.addAttribute("bodyContent", "home" );
        return "master";
    }

}
