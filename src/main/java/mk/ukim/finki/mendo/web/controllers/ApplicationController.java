package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{cycleId}")
    @ResponseBody
    public Map<String, Object> registerForCompetition(
            @PathVariable Long cycleId,
            @RequestParam Grade grade,
            @RequestParam Long schoolId
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (applicationService.isUserAlreadyRegistered(cycleId)) {
                response.put("success", false);
                response.put("message", "Веќе сте регистрирани за овој натпревар.");
                return response;
            }

            applicationService.registerForCompetition(cycleId, grade, schoolId);
            response.put("success", true);
            response.put("message", "Успешно се пријавивте за натпреварот.");

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }
}