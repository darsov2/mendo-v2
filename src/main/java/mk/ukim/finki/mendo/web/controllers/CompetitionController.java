package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Task;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.TaskService;
import mk.ukim.finki.mendo.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.web.mapper.RoomMapper;
import mk.ukim.finki.mendo.web.request.CompetitionRequest;
import mk.ukim.finki.mendo.service.CompetitionCycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

    private final CompetitionCycleService competitionCycleService;
    private final CompetitionMapper competitionMapper;
    private final CompetitionService competitionService;
    private final TaskService taskService;
    private final RoomMapper roomMapper;

    public CompetitionController(CompetitionCycleService competitionCycleService, CompetitionMapper competitionMapper, CompetitionService competitionService, TaskService taskService, RoomMapper roomMapper) {
        this.competitionCycleService = competitionCycleService;
        this.competitionMapper = competitionMapper;
        this.competitionService = competitionService;
        this.taskService = taskService;
        this.roomMapper = roomMapper;
    }

    @GetMapping("/add")
    public String getCompetition(Model model) {
//        Task task = new Task();
//        task.setTitle("New Task");
//        task.setId(1L);
        model.addAttribute("cycles", competitionCycleService.findAll());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        model.addAttribute("rooms", roomMapper.findAllRooms());
        model.addAttribute("tasks",taskService.getAllTasks());
//        model.addAttribute("tasks", Arrays.asList(task));
        model.addAttribute("bodyContent", "admin/addCompetition");

        return "master";
    }
    @GetMapping("/edit/{id}")
    public String getEditCompetition(@PathVariable Long id, Model model) {
        model.addAttribute("cycles", competitionCycleService.findAll());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        model.addAttribute("rooms", roomMapper.findAllRooms());
        model.addAttribute("tasks",taskService.getAllTasks());
        model.addAttribute("competition", competitionMapper.toCompetitionRequest(competitionMapper.findById(id)));
        model.addAttribute("competitionId", id);
        model.addAttribute("bodyContent", "admin/editCompetition");
        return "master";
    }

    @PostMapping("/add")
    public String addCompetition(CompetitionRequest request, Model model) {

        if (competitionMapper.addCompetition(request) == null) {
            throw new RuntimeException("Can't add competition");
        }

        return "redirect:/competition";
    }


    @PostMapping("/edit/{id}")
    public String editCompetition(@PathVariable Long id,
                                  CompetitionRequest request,
                                  Model model) {
        competitionMapper.editCompetition(id, request)
                .orElseThrow(() -> new RuntimeException("Can't update competition with id: " + id));
        return "redirect:/competition";
    }


}
