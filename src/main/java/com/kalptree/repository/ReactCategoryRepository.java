package com.kalptree.repository;

import com.kalptree.entity.ReactCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactCategoryRepository extends JpaRepository<ReactCategories, Integer> {
    Optional<ReactCategories> findByReactName(String reactName);
}
