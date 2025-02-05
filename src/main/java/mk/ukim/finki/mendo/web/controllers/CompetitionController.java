package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.web.mapper.RoomMapper;
import mk.ukim.finki.mendo.web.request.CompetitionRequest;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

    private final CompetitionCycleService competitionCycleService;
    private final CompetitionMapper competitionMapper;
    private final CompetitionService competitionService;
    private final RoomMapper roomMapper;

    public CompetitionController(CompetitionCycleService competitionCycleService, CompetitionMapper competitionMapper, CompetitionService competitionService, RoomMapper roomMapper) {
        this.competitionCycleService = competitionCycleService;
        this.competitionMapper = competitionMapper;
        this.competitionService = competitionService;
        this.roomMapper = roomMapper;
    }

    @GetMapping("/add")
    public String getCompetition(Model model) {
        model.addAttribute("cycles", competitionCycleService.findAll());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        model.addAttribute("rooms", roomMapper.findAllRooms());
        model.addAttribute("bodyContent", "admin/addCompetition");

        return "master";
    }



    @PostMapping("/add")
    public String addCompetition(CompetitionRequest request, Model model) {

        if (competitionMapper.addCompetition(request) == null) {
            throw new RuntimeException("Can't add competition");
        }

        return "redirect:/competition";
    }


}
