package com.kalptree.service;

import com.kalptree.entity.Categories;
import com.kalptree.entity.ReactCategories;
import com.kalptree.exception.CategoryAlreadyExistException;
import com.kalptree.exception.ReactCategoryAlreadyExistException;
import com.kalptree.repository.CategoryRepository;
import com.kalptree.repository.ReactCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kalptree.response.ResponseMessageConstants.categoryAlreadyExist;
import static com.kalptree.response.ResponseMessageConstants.reactCategoryAlreadyExist;

@Service
public class AdminService {

    private final CategoryRepository categoryRepository;
    private final ReactCategoryRepository reactCategoryRepository;

    public AdminService(CategoryRepository categoryRepository, ReactCategoryRepository reactCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.reactCategoryRepository = reactCategoryRepository;
    }

    public Categories addCategory(Categories category) throws CategoryAlreadyExistException {
        Optional<Categories> optionalCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistException(categoryAlreadyExist);
        }
        return categoryRepository.save(category);
    }

    public List<Categories> fetchAllCategory() {
        return categoryRepository.findAll();
    }

    public ReactCategories addReactCategory(ReactCategories react) throws ReactCategoryAlreadyExistException {
        Optional<ReactCategories> optionalReactCategory = reactCategoryRepository.findByReactName(react.getReactName());
        if (optionalReactCategory.isPresent()) {
            throw new ReactCategoryAlreadyExistException(reactCategoryAlreadyExist);
        }
        return reactCategoryRepository.save(react);
    }
}
