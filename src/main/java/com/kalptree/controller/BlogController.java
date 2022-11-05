package com.kalptree.controller;

import com.kalptree.entity.Blogs;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kalptree.response.ResponseMessageConstants.successBlogAdded;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> addBlog(@Valid @RequestBody Blogs blog) {
        Blogs newBlog = blogService.addBlog(blog);
        return ResponseHandler.generateResponse(successBlogAdded, HttpStatus.CREATED, newBlog);
    }

    // TODO: ADD THIS FUNCTIONALITY
//    @GetMapping("/all/{email}")
//    public ResponseEntity<?> viewBlogsOfUser(@PathVariable("email") String email) {
//        Blogs newBlog = blogService.addBlog(blog);
//        return ResponseHandler.generateResponse(successBlogAdded, HttpStatus.CREATED, newBlog);
//    }
}
