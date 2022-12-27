package com.kalptree.controller;

import com.kalptree.entity.Blogs;
import com.kalptree.exception.AuthenticationException;
import com.kalptree.model.BlogsOfUserResponse;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


import static com.kalptree.response.ResponseMessageConstants.*;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> addBlog(@Valid @RequestBody Blogs blog) {
        // TODO: check if the category or userId entered is valid in database or not if not then throw appropriate exceptions and response
        Blogs newBlog = blogService.addBlog(blog);
        return ResponseHandler.generateResponse(successBlogAdded, HttpStatus.CREATED, newBlog);
    }

    @GetMapping("all/{userId}")
    public ResponseEntity<?> getBlogsOfUser(@PathVariable("userId") Long userId) {

        List<Blogs> blogs;
        List<BlogsOfUserResponse> userBlogs = new ArrayList<>();
        try {
            blogs = blogService.getBlogsOfUser(userId);
            blogs.forEach(blog -> userBlogs.add(new BlogsOfUserResponse(
                    blog.getBlogId(), blog.getUser().getUserId(),
                    blog.getTitle(), blog.getContent(), blog.getCategory().getCategoryId(), blog.getCategory().getCategoryName(),
                    blog.getPenName(), blog.getLikeCount(), blog.getFunnyCount(), blog.getInsightfulCount(),
                    blog.getCreationDateTime(), blog.getWrittenDateTime(), blog.getPublishDateTime(), blog.getModifiedDateTime())));

        } catch (AuthenticationException e) {
            return ResponseHandler.generateResponse(userNotFound, HttpStatus.UNAUTHORIZED, null);
        }
        return ResponseHandler.generateResponse(successUserBlogsGet, HttpStatus.OK, userBlogs);
    }
}
