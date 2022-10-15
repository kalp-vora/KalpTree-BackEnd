package com.kalptree.service;

import com.kalptree.entity.Blogs;
import com.kalptree.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blogs addBlog(Blogs blog) {
        return blogRepository.save(blog);
    }
}
