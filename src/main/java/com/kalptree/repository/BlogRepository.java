package com.kalptree.repository;

import com.kalptree.entity.Blogs;
import com.kalptree.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long> {
    List<Blogs> findByUser(Users user);
}
