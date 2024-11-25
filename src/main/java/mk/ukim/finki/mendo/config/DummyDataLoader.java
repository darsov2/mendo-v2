package mk.ukim.finki.mendo.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mendo.model.Category;
import mk.ukim.finki.mendo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class DummyDataLoader {
    private final CategoryRepository categoryRepository;

    public DummyDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    private void init() {
//        Category category = new Category();
//        category.setName("Категорија 1");
//        categoryRepository.save(category);
//
//        Category category2 = new Category();
//        category2.setName("Категорија 2");
//        category2.setParentCategory(category);
//        categoryRepository.save(category2);
//
//        Category category3 = new Category();
//        category3.setName("Катеорија 3");
//        categoryRepository.save(category3);
    }
}
