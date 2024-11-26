package mk.ukim.finki.mendo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("bodyContent","home");
        return "master";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("bodyContent","register");
        return "master";
    }
}
