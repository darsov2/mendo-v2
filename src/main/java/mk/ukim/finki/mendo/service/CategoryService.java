package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAllParent();
    Category findById(Long id);
    Category save(Category category);
    List<Category> findAllByIds(List<Long> ids);
}
