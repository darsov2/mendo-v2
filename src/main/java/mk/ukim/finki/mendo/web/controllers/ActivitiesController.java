package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.dto.CategoryDTO;
import mk.ukim.finki.mendo.web.mapper.CategoryMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/activites")
public class ActivitiesController {
    private final CategoryMapper categoryMapper;

    public ActivitiesController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public String activities(Model model) {
        List<CategoryDTO> categories = categoryMapper.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent","admin/activites");
        return "master";
    }
}
