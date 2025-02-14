package mk.ukim.finki.mendo.service.impl;

import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByIds(List<Long> ids) {
        return categoryRepository.findAllByIdIn(ids);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
