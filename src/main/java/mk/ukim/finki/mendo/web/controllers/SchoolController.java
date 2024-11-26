package mk.ukim.finki.mendo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schools")
public class SchoolController {
    @GetMapping
    public String schools() {
        return "schools";
    }
}
