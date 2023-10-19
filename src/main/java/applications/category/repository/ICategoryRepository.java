package applications.category.repository;

import applications.category.Category;

import java.util.List;

public interface ICategoryRepository {
    Category createCategory(Category category);

    Long countCategoryByName(String categoryName);

    List<Category> getAll();
}
