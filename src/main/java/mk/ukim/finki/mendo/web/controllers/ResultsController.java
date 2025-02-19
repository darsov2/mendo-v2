package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import mk.ukim.finki.mendo.model.dto.ResultDTO;
import mk.ukim.finki.mendo.model.enums.Rank;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.ResultsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultsController {
    private final ResultsService resultsService;
    private final CompetitionService competitionService;
    private final AuthorizationService authorizationService;

    public ResultsController(ResultsService resultsService, CompetitionService competitionService, AuthorizationService authorizationService) {
        this.resultsService = resultsService;
        this.competitionService = competitionService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/competitionId={id}")
    public String getCompetitionResults(
            @PathVariable Long id,
            Model model
    ) {
        authorizationService.canViewResults();

        List<ResultDTO> results = resultsService.getResultOfCompetition(id);
        Competition competition = competitionService.findById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("results", results);

        model.addAttribute("bodyContent", "competition-results");

        return "master";
    }

    @GetMapping("/{id}/awards")
    public String addRewardsToParticipants(
            @PathVariable Long id,
            Model model
    ) {
        authorizationService.canManageResults();

        List<ResultDTO> results = resultsService.getResultOfCompetition(id);
        Competition competition = competitionService.findById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("results", results);
        model.addAttribute("ranks", Rank.values());


        model.addAttribute("bodyContent", "competition-results-awards");

        return "master";
    }





}
