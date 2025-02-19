package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.*;
import mk.ukim.finki.mendo.model.dto.CycleOrCompetitionDTO;
import mk.ukim.finki.mendo.model.dto.OptionDTO;
import mk.ukim.finki.mendo.model.enums.CompetitionTypes;
import mk.ukim.finki.mendo.model.enums.Grade;
import mk.ukim.finki.mendo.service.*;
import mk.ukim.finki.mendo.web.mapper.*;
import mk.ukim.finki.mendo.web.request.CompetitionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/competition")
public class CompetitionsController {

    private final CompetitionService competitionService;
    private final CompetitionCycleService competitionCycleService;
    private final SchoolService schoolService;
    private final MendoUserService mendoUserService;
    private final CompetitionMapper competitionMapper;
    private final QuotaMapper quotaMapper;
    private final ParticipationMapper participationMapper;
    private final RoomMapper roomMapper;
    private final UtilsMapper utilsMapper;
    private final TaskService taskService;

    public CompetitionsController(CompetitionService competitionService,
                                  CompetitionCycleService competitionCycleService, SchoolService schoolService, MendoUserService mendoUserService, CompetitionMapper competitionMapper, QuotaMapper quotaMapper, ParticipationMapper participationMapper, RoomMapper roomMapper, UtilsMapper utilsMapper, TaskService taskService) {
        this.competitionService = competitionService;
        this.competitionCycleService = competitionCycleService;
        this.schoolService = schoolService;
        this.mendoUserService = mendoUserService;
        this.competitionMapper = competitionMapper;
        this.quotaMapper = quotaMapper;
        this.participationMapper = participationMapper;
        this.roomMapper = roomMapper;
        this.utilsMapper = utilsMapper;
        this.taskService = taskService;
    }

//    @GetMapping
//    public String home(Model model) {
//
//
//
//        List<School> schools = schoolService.findAll();
//        List<CompetitionCycle> cycles = competitionCycleService.findAllSortedByYearDesc();
//        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;
//
//        Map<Long, List<Competition>> competitionsByCycle = cycles.stream()
//                .collect(Collectors.toMap(
//                        CompetitionCycle::getId,
//                        cycle -> competitionService.findAllByCycleId(cycle.getId())
//                ));
//
//        model.addAttribute("currentDateTime", LocalDateTime.now());
//        model.addAttribute("competitionCycles", cycles);
//        model.addAttribute("competitionsByCycle", competitionsByCycle);
//        model.addAttribute("bodyContent", "competitions");
//        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("selectedCycle", new CompetitionCycle());
//        model.addAttribute("schools", schools);
//        model.addAttribute("grades", Grade.values());
//
//        return "master";
//    }

    @GetMapping("")
    public String allCompetitions(Model model) {
        List<School> schools = schoolService.findAll();
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        List<CycleOrCompetitionDTO> cyclesOrCompetitions = competitionMapper.getCyclesOrCompetitions();
        //todo не го листа циклусот ако нема натпревар во него
        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute( "cyclesOrCompetitions", cyclesOrCompetitions);
        model.addAttribute("bodyContent", "competitions");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());


//        model.addAttribute("bodyContent", "allCompetitions");
        return "master";

    }

    @GetMapping("/add")
    public String getCompetition(Model model) {
        List<OptionDTO<Long>> moderators = utilsMapper.getAllModeratorUsersAsOptions();
        model.addAttribute("cycles", utilsMapper.getAllUpcomingCyclesAsOptions());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        model.addAttribute("rooms", utilsMapper.getAllRoomsAsOptions());
        model.addAttribute("tasks",utilsMapper.getAllTasksAsOptions());
        model.addAttribute("moderators", moderators);
        model.addAttribute("bodyContent", "admin/addCompetition");

        return "master";
    }

    @GetMapping("/edit/{id}")
    public String getEditCompetition(@PathVariable Long id, Model model) {
        model.addAttribute("cycles", utilsMapper.getAllUpcomingCyclesAsOptions());
        model.addAttribute("types", CompetitionTypes.values());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        model.addAttribute("rooms", utilsMapper.getAllRoomsAsOptions());
        model.addAttribute("tasks",utilsMapper.getAllTasksAsOptions());
        model.addAttribute("competition", competitionMapper.toCompetitionRequest(competitionMapper.findById(id)));
        model.addAttribute("competitionId", id);
        model.addAttribute("moderators", utilsMapper.getAllModeratorUsersAsOptions());
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



    @GetMapping("/{id}")
    public String getCompetitionDetails(@PathVariable Long id, Model model) {
        List<School> schools = schoolService.findAll();
        Competition competition = competitionService.findById(id);
        MendoUser currentUser = mendoUserService.getCurrentUser().isPresent() ? mendoUserService.getCurrentUser().get() : null;

        model.addAttribute("competition", competition);
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("quotas", quotaMapper.findQuotasForCompetition(id));
        model.addAttribute("currentDateTime", LocalDateTime.now());
        model.addAttribute("schools", schools);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("bodyContent", "competition/competition-details");
        return "master";

    }


    @GetMapping("/{id}/schedule")
    public String getSchedule(Model model, @PathVariable Long id){
        List<Participation> participations = participationMapper.getParticipantsForCompetition(id);
        model.addAttribute("participations", participations);
        model.addAttribute("competitionId", id);

        model.addAttribute("bodyContent", "competition/competition-schedule");
        return "master";
    }

    @GetMapping("/{id}/edit/schedule")
    public String getEditSchedule(Model model, @PathVariable Long id){
        List<Participation> participations = participationMapper.getParticipantsForCompetition(id);
        model.addAttribute("participations", participations);
        model.addAttribute("competitionId", id);
        model.addAttribute("rooms", roomMapper.findAllRooms());
        model.addAttribute("bodyContent", "competition/competition-schedule-edit");
        return "master";
    }

    @PostMapping("/{id}/edit/schedule")                //<participantId, roomId>
    public String editSchedule(@PathVariable Long id, @RequestParam Map<String, String> allParams){
        participationMapper.changeRoomForParticipation(id, allParams);
        return "redirect:/competition/" + id +"/schedule";
    }


    @GetMapping("/{id}/generate")
    public String generateSchedule(Model model, @PathVariable Long id) {
        List<Participation> participations = competitionService.distributeStudentsForCompetition(id);
        model.addAttribute("participations", participations);
        model.addAttribute("competitionId", id);
        model.addAttribute("bodyContent", "competition/competition-schedule");
        return "master";
    }
}