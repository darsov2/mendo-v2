package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.repository.CategoryRepository;
import mk.ukim.finki.mendo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllParent() {
        return categoryRepository.findAllByParentCategoryIsNull();
    }
}
