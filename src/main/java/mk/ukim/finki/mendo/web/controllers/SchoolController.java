package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.School;
import mk.ukim.finki.mendo.service.CompetitionService;
import mk.ukim.finki.mendo.service.SchoolService;
import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.web.mapper.SchoolMapper;
import mk.ukim.finki.mendo.web.request.SchoolRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schools")
public class SchoolController {
    public final SchoolService schoolService;
    public final SchoolMapper mapper;
    public final CompetitionCycleMapper cycleMapper;
    public final CompetitionMapper competitionMapper;
    private final SchoolMapper schoolMapper;

    public SchoolController(SchoolService schoolService, SchoolMapper mapper, CompetitionCycleMapper cycleMapper, CompetitionMapper competitionMapper, SchoolMapper schoolMapper) {
        this.schoolService = schoolService;
        this.mapper = mapper;
        this.cycleMapper = cycleMapper;
        this.competitionMapper = competitionMapper;
        this.schoolMapper = schoolMapper;
    }

    @GetMapping({"/", ""})
    public String schools(Model model){
        model.addAttribute("schools", mapper.listSchools());
        model.addAttribute("bodyContent", "schools");
        return "master";
    }
    @GetMapping({ "/add"})
    public String schoolForm(Model model){
        model.addAttribute("bodyContent", "admin/addSchool");
        return "master";
    }

    @PostMapping("/add")
    public String addSchool(SchoolRequest request, Model model){
        mapper.saveSchool(request);
//        model.addAttribute("bodyContent", "schools");
        return "redirect:/schools";
    }

    @GetMapping("/edit/{id}")
    public String editSchools(@PathVariable Long id, Model model ){
        School school = schoolMapper.findById(id);
        model.addAttribute("school", school);
        model.addAttribute("bodyContent", "admin/edit-school");
        return "master";
    }

    @PostMapping("/edit/{id}")
    public String updateSchools(@PathVariable Long id, SchoolRequest request){
        mapper.updateSchool(id, request);
        return "redirect:/schools";
    }




}





