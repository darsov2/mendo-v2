package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{cycleId}")
    public String registerForCompetition(
            @PathVariable Long cycleId,
            @RequestParam Grade grade,
            @RequestParam Long schoolId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            applicationService.registerForCompetition(cycleId, grade, schoolId);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Успешно се пријавивте за натпреварот");

        } catch (Exception e){
            return "master";
        }

        return "redirect:/competitions";
    }
}
