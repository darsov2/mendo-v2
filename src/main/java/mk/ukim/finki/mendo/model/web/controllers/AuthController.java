package mk.ukim.finki.mendo.model.web.controllers;

import mk.ukim.finki.mendo.model.web.mapper.AuthMapper;
import mk.ukim.finki.mendo.model.web.request.UserRegisterRequest;
import mk.ukim.finki.mendo.service.impl.GradesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final GradesService gradesService;
    private final AuthMapper authMapper;

    public AuthController(GradesService gradesService, AuthMapper authMapper) {
        this.gradesService = gradesService;
        this.authMapper = authMapper;
    }


    @GetMapping("register")
    public String registerForm(Model model) {
        model.addAttribute("bodyContent","register");
        model.addAttribute("grades", gradesService.getGradesAsOptions());
        return "master";
    }

    @PostMapping("register")
    public String register(Model model, UserRegisterRequest userRegisterRequest) {
        authMapper.registerUser(userRegisterRequest);
        model.addAttribute("bodyContent","register");
        model.addAttribute("grades", gradesService.getGradesAsOptions());
        return "master";
    }
}