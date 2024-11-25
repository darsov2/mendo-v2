package mk.ukim.finki.mendo.model.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/activites")
public class ActivitiesController {
    @GetMapping
    public String activities(Model model) {
        model.addAttribute("bodyContent","admin/activites");
        return "master";
    }
}
