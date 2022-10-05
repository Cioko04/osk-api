package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.request.CategoryRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryService {
    List<CategoryRequest> getCategories();

    List<CategoryRequest> getCategoriesWithCourses();

    Category getCategory(Long id);

    CategoryRequest getCategoryWithCourses(Long id);

    void saveCategory(Category category);

    void deleteCategory(Long id);

    @Transactional
    void updateCategory(Long id,
                            CategoryType categoryType,
                            Integer price,
                            Integer time);
}
