package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.web.mapper.CompetitionCycleMapper;
import mk.ukim.finki.mendo.web.mapper.CompetitionMapper;
import mk.ukim.finki.mendo.web.mapper.QuotaMapper;
import mk.ukim.finki.mendo.web.mapper.SchoolMapper;
import mk.ukim.finki.mendo.web.request.QuotasRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/quotas")
public class QuotaController {

    private final SchoolMapper schoolMapper;
    private final CompetitionCycleMapper cycleMapper;
    private final CompetitionMapper competitionMapper;
    private final QuotaMapper mapper;

    public QuotaController(SchoolMapper schoolMapper, CompetitionCycleMapper cycleMapper, CompetitionMapper competitionMapper, QuotaMapper mapper) {
        this.schoolMapper = schoolMapper;
        this.cycleMapper = cycleMapper;
        this.competitionMapper = competitionMapper;
        this.mapper = mapper;
    }


    @GetMapping("/add")
    public String addQuotaToSchool(Model model){
        model.addAttribute("bodyContent", "addQuotasToSchool");
        model.addAttribute("schools", schoolMapper.listSchools());
        model.addAttribute("competitions", competitionMapper.listCompetitions());
        return "master";
    }

    @PostMapping("/add")
    public String addQuota(@RequestParam Long competitionId, @RequestParam Map<String, String> allParams){
        mapper.bulkSave(competitionId, allParams);
        return "redirect:/competition/" + competitionId;
    }

    @PostMapping("/edit/{id}")
    public String editQuota(@PathVariable Long id, @RequestParam Map<String, String> allParams){
        mapper.editBulkSave(id, allParams);
        return "redirect:/competition/" + id;
    }

}
