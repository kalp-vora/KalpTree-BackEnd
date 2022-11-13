package com.kalptree.service;

import com.kalptree.entity.Blogs;
import com.kalptree.entity.Users;
import com.kalptree.exception.AuthenticationException;
import com.kalptree.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blogs addBlog(Blogs blog) {
        return blogRepository.save(blog);
    }

    public List<Blogs> getBlogsOfUser(Long userId) throws AuthenticationException {
        Users user = new Users();
        user.setUserId(userId);
        List<Blogs> blogsOfUser = blogRepository.findByUser(user);
        if (blogsOfUser.isEmpty()) {
            throw new AuthenticationException("USER NOT FOUND WITH ID: " + userId);
        }
        return blogsOfUser;
    }
}
