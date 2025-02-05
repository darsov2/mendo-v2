package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.mapper.AuthMapper;
import mk.ukim.finki.mendo.web.request.UserRegisterRequest;
import mk.ukim.finki.mendo.service.impl.GradesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final GradesService gradesService;
  private final AuthMapper authMapper;
  private final SchoolService schoolService;

  public AuthController(GradesService gradesService, AuthMapper authMapper,
      SchoolService schoolService) {
    this.gradesService = gradesService;
    this.authMapper = authMapper;
    this.schoolService = schoolService;
  }


  @GetMapping("register")
  public String registerForm(Model model) {
    model.addAttribute("bodyContent", "register");
    model.addAttribute("grades", gradesService.getGradesAsOptions());
    model.addAttribute("schools", schoolService.findAll());
    return "master";
  }

  @PostMapping("register")
  public String register(Model model, UserRegisterRequest userRegisterRequest) {
    authMapper.registerUser(userRegisterRequest);
    model.addAttribute("bodyContent", "register");
    model.addAttribute("grades", gradesService.getGradesAsOptions());
    return "master";
  }
}
