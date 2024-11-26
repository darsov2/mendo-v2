package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.model.dto.CategoryDTO;
import mk.ukim.finki.mendo.service.CategoryService;
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

    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAllParent().stream()
                .map(category -> toDTO(category, new HashSet<Long>()))
                .toList();
    }

    public static CategoryDTO toDTO(Category category, Set<Long> processedCategories) {
        if (category == null || processedCategories.contains(category.getId())) {
            return null; // Avoid infinite recursion
        }

        // Mark the current category as processed
        processedCategories.add(category.getId());

        // Map the category to a DTO
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        // Map children, avoiding cycles
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
}