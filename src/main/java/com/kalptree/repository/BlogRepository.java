package com.kalptree.repository;

import com.kalptree.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long> {
    // TODO: ADD THIS FUNCTIONALITY
    //Optional<Blogs> findByUser();
}
