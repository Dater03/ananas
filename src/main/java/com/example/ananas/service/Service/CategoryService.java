package com.example.ananas.service.Service;

import com.example.ananas.dto.request.CategoryCreateRequest;
import com.example.ananas.entity.Category;
import com.example.ananas.repository.Category_Repository;
import com.example.ananas.service.IService.ICategoryService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    final Category_Repository categoryRepository;
    @Override
    public Category createCategory(CategoryCreateRequest category) {
        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setDescription(category.getDescription());
        return this.categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(CategoryCreateRequest category, int id) {
        Category updateCategory = this.categoryRepository.findById(id).get();
        updateCategory.setDescription(category.getDescription());
        updateCategory.setCategoryName(category.getCategoryName());
        return this.categoryRepository.save(updateCategory);
    }

    @Override
    public Boolean deleteCategory(int id) {
        if(this.categoryRepository.findById(id).get() == null)
            return false;
        else
        {
            this.categoryRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public Category getOneCategory(int id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Boolean exisById(int id) {
        return this.categoryRepository.existsById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }
}
