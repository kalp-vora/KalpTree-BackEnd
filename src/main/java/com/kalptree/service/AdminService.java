package com.kalptree.service;

import com.kalptree.entity.Categories;
import com.kalptree.exception.CategoryAlreadyExistException;
import com.kalptree.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kalptree.response.ResponseMessageConstants.categoryAlreadyExist;

@Service
public class AdminService {

    private final CategoryRepository categoryRepository;

    public AdminService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Categories addCategory(Categories category) throws CategoryAlreadyExistException {
        Optional<Categories> optionalCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistException(categoryAlreadyExist);
        }
        return categoryRepository.save(category);
    }
}
