package com.kalptree.controller;

import com.kalptree.entity.Categories;
import com.kalptree.entity.ReactCategories;
import com.kalptree.exception.CategoryAlreadyExistException;
import com.kalptree.exception.ReactCategoryAlreadyExistException;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kalptree.response.ResponseMessageConstants.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/category/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Categories category) {
        Categories newCategory;
        try {
            newCategory = adminService.addCategory(category);
        } catch (CategoryAlreadyExistException e) {
            return ResponseHandler.generateResponse(categoryAlreadyExist, HttpStatus.CONFLICT, null);
        }
        return ResponseHandler.generateResponse(successCategoryAdded, HttpStatus.CREATED, newCategory);
    }

    @PostMapping("/react/add")
    public ResponseEntity<?> addReactCategory(@Valid @RequestBody ReactCategories reactCategory) {
        ReactCategories newReact;
        try {
            newReact = adminService.addReactCategory(reactCategory);
        } catch (ReactCategoryAlreadyExistException e) {
            return ResponseHandler.generateResponse(reactCategoryAlreadyExist, HttpStatus.CONFLICT, null);
        }
        return ResponseHandler.generateResponse(successReactCategoryAdded, HttpStatus.CREATED, newReact);
    }
}
