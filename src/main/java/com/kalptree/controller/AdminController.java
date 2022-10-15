package com.kalptree.controller;

import com.kalptree.entity.Categories;
import com.kalptree.exception.CategoryAlreadyExistException;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kalptree.response.ResponseMessageConstants.categorySuccess;
import static com.kalptree.response.ResponseMessageConstants.categoryAlreadyExist;

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
            return new ResponseEntity<>(ResponseHandler.generateResponse(categoryAlreadyExist, HttpStatus.CONFLICT, null), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ResponseHandler.generateResponse(categorySuccess, HttpStatus.CREATED, newCategory), HttpStatus.CREATED);
    }
}
