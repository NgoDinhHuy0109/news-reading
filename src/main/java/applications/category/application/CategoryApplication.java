package applications.category.application;

import applications.category.Category;
import applications.category.service.CategoryService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CategoryApplication {
    CategoryService categoryService = new CategoryService();

    public Category createCategory(Category category) throws Exception {
        //validate data
        if (StringUtils.isEmpty(category.getCategoryName())) {
            throw new Exception("Category name can not be not empty");
        }
        if(categoryService.countCategoryByName(category.getCategoryName()) > 1) {
            throw new Exception("Category name existed ");
        }


        return categoryService.createCategory(category);
    }
    public List<Category> getAllCategories(){
        return categoryService.getAll();
    }
}
