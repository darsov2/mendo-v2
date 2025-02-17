package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.dto.CategoryDTO;
import mk.ukim.finki.mendo.service.CategoryService;
import mk.ukim.finki.mendo.web.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryMapper {
    private final CategoryService categoryService;

    public CategoryMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<CategoryDTO> getAllCategories(Long selectedCategory) {
        return categoryService.findAllParent().stream()
                .map(category -> toDTO(category, new HashSet<Long>()))
                .peek(categoryDTO -> categoryDTO.setIsSelected(categoryDTO.getId() == selectedCategory))
                .toList();
    }

    public Category addCategory(CategoryRequest request) {
        Category parentCategory = null;
        if(request.getParentCategoryId() != null) {
            parentCategory = findById(request.getParentCategoryId());
        }
        List<Category> categories = findAllByIds(request.getChildrenId());
        return categoryService.save(new Category(request.getName(), parentCategory, categories));
    }

    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    public List<Category> findAllByIds(List<Long> ids) {
        return categoryService.findAllByIds(ids);
    }

    public List<Category> findAllCategories(){
        return categoryService.findAll();
    }

    public static CategoryDTO toDTO(Category category, Set<Long> processedCategories) {
        if (category == null || processedCategories.contains(category.getId())) {
            return null;
        }

        processedCategories.add(category.getId());

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getChildren() != null) {
            dto.setChildren(
                    category.getChildren().stream()
                            .map(child -> toDTO(child, processedCategories))
                            .filter(Objects::nonNull) // Remove nulls
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public Category editCategory(CategoryRequest request, Long id) {
        Category category = findById(id);
        category.setParentCategory(findById(request.getParentCategoryId()));
        category.setChildren(findAllByIds(request.getChildrenId()));
        return categoryService.save(category);
    }

    public void deleteCategory(Long id) {
        categoryService.deleteById(id);
    }
}