package applications.category.application;

import applications.category.Category;
import applications.category.service.CategoryService;

public class CategoryApplication {
    CategoryService categoryService = new CategoryService();

    public Category createCategory(Category category) {
        return categoryService.createCategory(category);
    }
}
