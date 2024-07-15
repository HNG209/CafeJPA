package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByName(String name);
}
