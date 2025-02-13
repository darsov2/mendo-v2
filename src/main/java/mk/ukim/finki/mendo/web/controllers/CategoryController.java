package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.web.mapper.CategoryMapper;
import mk.ukim.finki.mendo.web.request.CategoryRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public String category(Model model) {
        model.addAttribute("categories", categoryMapper.findAllCategories());
        model.addAttribute("bodyContent", "list-category");
        return "master";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("categories", categoryMapper.findAllCategories());
        model.addAttribute("bodyContent", "add-category");
        return "master";
    }

    @PostMapping("/add")
    public String addCategoryForm(CategoryRequest request, Model model) {
        categoryMapper.addCategory(request);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryMapper.deleteCategory(id);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(Model model, @PathVariable Long id) {
        model.addAttribute("category", categoryMapper.findById(id));
        model.addAttribute("categories", categoryMapper.findAllCategories());
        model.addAttribute("bodyContent", "edit-category");
        return "master";
    }

    @PostMapping("/edit/{id}")
    public String editCategoryForm(CategoryRequest request, Model model, @PathVariable Long id) {
        categoryMapper.editCategory(request, id);
        return "redirect:/category";

    }
}
