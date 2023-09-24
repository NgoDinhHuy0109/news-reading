package applications.category.service;

import applications.category.Category;
import applications.category.repository.CategoryRepository;
import applications.category.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryService {
    ICategoryRepository iCategoryRepository = new CategoryRepository();

    public Category createCategory(Category category) {
        return iCategoryRepository.createCategory(category);
    }
}
