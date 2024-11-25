package mk.ukim.finki.mendo.model.web.controllers;

import mk.ukim.finki.mendo.model.Competition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {
    @GetMapping("")
    public String getCompetition(Model model) {
        model.addAttribute("bodyContent", "admin/addCompetition" );
        return "master";
    }

}
